# Job Board collecting API

Simple API to collect info from a Job board, and store it in H2 database.
In this API provided functionality to get info about all jobs with pagination and also provides statistics about each city (location) with jobs count;

Jobs updates each hour (can be configured)

Retrofit2 used for remote call to Jobs Board API
(remote resource url - https://www.arbeitnow.com/api/job-board-api)

### Get jobs data:
Method | Endpoint                                        | Description              |
|-----| -------------------------------------------------| ------------------------ |
| `GET` | `/api/v1/jobs?page=?&size=?&order=?&sort=?,?,?`| Returns a list of jobs   |


Available parameters:
```
page - number of page, by default it's 1

size - number of provided jobs on one page, by default it's 10

order - in which direction result will be ordered,
by default it's "ASC"

sort - list of values by which result will be sorted 
(values specified one by one, separated by commas),
by default it's "title" 
````

### Get jobs statistics:
|Method | Endpoint                    | Description                                                    |
|-------| ----------------------------| ---------------------------------------------------------------|
| `GET` | `/api/v1/statistics?order=?`| Returns statistics for every location available with jobs count|


Available parameters:
```
order - in which direction result will be ordered,
by default it's "ASC"
````

### Scheduled service information

Service working by scheduled cron value, so it collects data from Jobs Board API and then processes it and stores in database.
There is also a parameter which tells a scheduler about how many pages to collect from API.
