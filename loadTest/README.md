# Website Load Testing

## Overview

This project uses [Locust](https://locust.io/) for load testing the Baykar Kariyer website (https://kariyer.baykartech.com). It focuses on validating various functionalities of the site, including navigation, open position filtering, and API responses.

## Project Structure

```
├── locustfiles
│   ├── navbar_test.py
│   ├── open_position_program_test.py
│   ├── open_position_program_type_test.py
│   ├── open_position_program_category_test.py
│   └── open_position_filter_test.py
├── utils
│   └── helper_functions.py
├── docker-compose.yml
└── README.md

```

### `locustfiles/`

Contains the load testing scripts that define user behavior and tasks:

- **`navbar_test.py`**: Tests the responsiveness of the website's navbar in both Turkish and English.
- **`open_position_program_test.py`**: Tests the API for retrieving open position programs.
- **`open_position_program_type_test.py`**: Tests the API for retrieving program types.
- **`open_position_program_category_test.py`**: Tests the API for retrieving program categories.
- **`open_position_filter_test.py`**: Tests the API for filtering open positions based on specified parameters.

### `utils/`

Contains utility functions that support the main testing scripts:

- **`helper_functions.py`**: Provides helper methods, such as `get_full_url`, to construct complete URLs for testing.


### Testing Logic
* <b>Navbar Tests:</b> Sends GET requests to various navbar URLs to ensure they are accessible and return a 200 status code.<br>
* <b>Open Position Tests:</b> Sends requests to endpoints to validate that the expected number of open positions and related details are returned.<br>
* <b>Program and Category Tests:</b> Validates API responses for program categories and types, ensuring all expected keys are present in the response.<br>
