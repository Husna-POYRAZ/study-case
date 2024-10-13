from locust import HttpUser, task, between
from utils.helper_functions import get_full_url


def validate_open_positions_response(data):
    # Validate the count and results length
    assert data["count"] == 8, f"Expected count 8, but got {data['count']}."
    assert len(data["results"]) == 8, f"Expected 8 results, but got {len(data['results'])}."

    # Check for expected keys in each result
    for item in data["results"]:
        assert "id" in item, "Result is missing 'id' key."
        assert "text" in item, "Result is missing 'text' key."
        assert "category" in item, "Result is missing 'category' key."
        assert "type" in item, "Result is missing 'type' key."
        assert "keywords_list" in item, "Result is missing 'keywords_list' key."
        print(f"Result: {item['text']} - ID: {item['id']} - Category: {item['category']['text']} - Type: {item['type']['text']}")


class OpenPositionFilterTestUser:
    def __init__(self, client):
        self.client = client
        self.BASE_URL = "https://kariyer.baykartech.com"

    def get_open_positions_by_filter(self):
        endpoint = "/tr/application/serializerProgram/"
        params = {
            "page": 1,
            "search": "",
            "program_category_ids": 50,
            "type": 1
        }
        url = get_full_url(self.BASE_URL, endpoint)
        response = self.client.get(url, params=params)

        if response.status_code == 200:
            print("Successfully accessed:", url)
            data = response.json()
            validate_open_positions_response(data)
        else:
            print(f"Unexpected status code: {response.status_code}")


class CombinedUser(HttpUser):
    wait_time = between(1, 3)

    @task
    def get_open_positions(self):
        test_user = OpenPositionFilterTestUser(self.client)
        test_user.get_open_positions_by_filter()

    # Include any other tasks you have here
