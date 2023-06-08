**Recruitment task - app to retrieve data from github API**
* Navigate to : https://github.com/pietrekpl/recruitmentTask
* Use Code/Clone
* In your IDE use code from version control and paste copied address of repository
* Using github API expects authentication so please use github access token. You canget it from https://github.com/settings/tokens
and paste it into application.yml to property api_key.
* You may run the app.
* ////////////
* Using Api Testing tool such as Postman head for default URL : http://localhost:8080/api/repositories (you might change port when you need it)
* By Using Post method you shall pass body in JSON format. Required information contains property username for example :

* {
"username": "twitter"
}

* Response contains List of repositories which are not forked, name of each repository, login of the repository owner,
 List of branches for each repository, name of each branch, and last commit sha for each branch.

* Choosing content type other than json will cause getting exception with code 406
* Choosing username which does not exist will cause getting exception with code 404
* /////////////
* You can also try version with docker
* Links here : https://hub.docker.com/repository/docker/pludynia/recruitment_task/general
