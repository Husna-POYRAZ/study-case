from locust import HttpUser, task, between
from locustfiles.navbar_test import NavbarTestUser
from locustfiles.open_position_program_test import OpenPositionProgramTestUser
from locustfiles.open_position_program_type_test import OpenPositionProgramTypeTestUser
from locustfiles.open_position_program_category_test import OpenPositionProgramCategoryTestUser
from locustfiles.open_position_filter_test import OpenPositionFilterTestUser


class CombinedUser(HttpUser):
    wait_time = between(1, 3)

    @task
    def navbar(self):
        NavbarTestUser(self.client).test_navbar()

    @task
    def open_position(self):
        OpenPositionProgramTestUser(self.environment).get_program()

    @task
    def get_program_type_list(self):
        OpenPositionProgramTypeTestUser(self.client).get_program_type_list()

    @task
    def get_program_category_list(self):
        OpenPositionProgramCategoryTestUser(self.client).get_program_category_list()

    @task
    def get_open_positions_by_filter(self):
        OpenPositionFilterTestUser(self.client).get_open_positions_by_filter()