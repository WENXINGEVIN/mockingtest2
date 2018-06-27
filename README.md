# Building a Website with Node.js and Express.js
Need to integrate with webpack to organize the resource.

## Installing
1. Make sure you have these installed
	- [react]
	- [springboot]
	- [eclipse]
	- [tomcat]
    - [maven]
    - [java]
2. Clone this repository into your local machine using the terminal (mac) or Gitbash (PC) `> git clone CLONEURL`
3. CD to the folder `cd FOLDERNAME`
* Run `./mvnw spring-boot:run` to install everything (mvnw.bat for Windows users)


## Possible Error Solution

* if mention about Java, check JAVA_HOME path, and version, java10 is not supported. [how to change java version](https://stackoverflow.com/questions/21964709/how-to-set-or-change-the-default-java-jdk-version-on-os-x)
* if mention about port being used, open process manager to kill java process and try again. 
* if everything fails, try mvn clean and build again.


## Code commit and contribution

### Create your branch off develop branch

1/ git checkout develop <br>

2/ git pull or git fetch origin -p (sync origin/develop and remove your local branch that was deleted on remote) <br>

3/ Create your branch with -b <br>
git checkout -b xiaofeng/add-img-slider <br>


 git add README.md

 git add app/routes/index.js

 git status


Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   README.md
        modified:   app/routes/index.js

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        node_modules/

Please do not commit node_modules.


## Send pull request to develop branch

* Always make your PR to develop branch
* Do not commit files in node_modules
* Do not commit hidden files .* files
 
## Merge from develop to master branch
Master branch is always the most trustable branch.

## Troubleshooting

* If you have any issues on not able to install mvn package, please 
  remove the node_modules dir in your project root.
* cd ~/.m2, and remove everything under it.