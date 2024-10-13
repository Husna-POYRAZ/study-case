from locust import HttpUser, task, between
from utils.helper_functions import get_full_url


def validate_response(data):
    # Validate the count and results length
    assert data["count"] == 2, f"Expected count 2, but got {data['count']}."
    assert len(data["results"]) == 2, f"Expected 2 results, but got {len(data['results'])}."

    # Check for expected keys in each result
    for item in data["results"]:
        assert "id" in item, "Result is missing 'id' key."
        assert "text" in item, "Result is missing 'text' key."
        assert "program_count" in item, "Result is missing 'program_count' key."
        print(f"Result: {item['text']} - ID: {item['id']} - Program Count: {item['program_count']}")


class OpenPositionProgramTypeTestUser:
    def __init__(self, client):
        self.client = client
        self.BASE_URL = "https://kariyer.baykartech.com"

    def get_program_type_list(self):
        endpoint = "/tr/application/serializerProgramTypeSerializerListViewSet/"
        url = get_full_url(self.BASE_URL, endpoint)
        response = self.client.get(url)

        if response.status_code == 200:
            print("Successfully accessed:", url)
            data = response.json()
            validate_response(data)
        else:
            print(f"Unexpected status code: {response.status_code}")


class CombinedUser(HttpUser):
    wait_time = between(1, 3)

    @task
    def get_program_type_list(self):
        test_user = OpenPositionProgramTypeTestUser(self.client)
        test_user.get_program_type_list()
