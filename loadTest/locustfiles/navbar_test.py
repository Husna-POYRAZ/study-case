from locust import HttpUser, task, between
from utils.helper_functions import get_full_url


class NavbarTestUser:
    def __init__(self, client):
        self.client = client
        self.BASE_URL = "https://kariyer.baykartech.com"

    def test_navbar(self):
        # List of endpoint paths for the navbar URLs
        navbar_endpoints = {
            "tr": [
                "/tr/yuksek-irtifa/",
                "/tr/#yerleskelerimiz",
                "/tr/#baykar-da-yasam",
                "/tr/#acik-pozisyonlar",
                "/tr/staj/",
                "/tr/",
                "/tr/hesaplar/login/?next=/tr/dashboard/"
            ],
            "en": [
                "/en/yuksek-irtifa/",
                "/en/#yerleskelerimiz",
                "/en/#baykar-da-yasam",
                "/en/#acik-pozisyonlar",
                "/en/internship/",
                "/en/",
                "/en/accounts/login/?next=/en/dashboard/"
            ]
        }

        # Iterate over each endpoint and send a GET request
        for lang, endpoints in navbar_endpoints.items():
            for endpoint in endpoints:
                url = get_full_url(self.BASE_URL, endpoint)
                response = self.client.get(url)

                if response.status_code == 200:
                    print(f"Successfully accessed: {url}")
                else:
                    print(f"Failed to access: {url} - Status code: {response.status_code}")


class CombinedUser(HttpUser):
    wait_time = between(1, 3)

    @task
    def navbar(self):
        NavbarTestUser(self.client).test_navbar()
