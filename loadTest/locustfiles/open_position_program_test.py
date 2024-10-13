from locust import HttpUser, task
from utils.helper_functions import get_full_url


def validate_response(response_data):
    assert "count" in response_data, "Response does not contain 'count'"
    assert isinstance(response_data["count"], int), "'count' is not an integer"
    assert "results" in response_data, "Response does not contain 'results'"
    assert isinstance(response_data["results"], list), "'results' is not a list"

    for result in response_data["results"]:
        assert "id" in result, "Result does not contain 'id'"
        assert "text" in result, "Result does not contain 'text'"
        assert "category" in result, "Result does not contain 'category'"
        assert "type" in result, "Result does not contain 'type'"
        assert "keywords_list" in result, "Result does not contain 'keywords_list'"

    print("Response validation passed!")


class OpenPositionProgramTestUser(HttpUser):
    def __init__(self, environment):
        super().__init__(environment)
        self.BASE_URL = "https://kariyer.baykartech.com"

    @task
    def get_program(self):
        endpoint = "/tr/application/serializerProgram/?page=1&search=&program_category_ids=&type=1"
        url = get_full_url(self.BASE_URL, endpoint)
        response = self.client.get(url)

        if response.status_code == 200:
            print("Successfully accessed:", url)
            validate_response(response.json())
        else:
            print(f"Failed to access: {url} - Status code: {response.status_code}")
