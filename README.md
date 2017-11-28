This bakery is distributed, so it has some problems. It will be used for the distributed systems dojos to help us understand these problems and the solutions to them.

#What is this?
This is a set of exercises to learn about distributed systems. What you will have to do is run the tests and fix them.

#Dependencies
* [Docker](https://docs.docker.com/engine/installation/): installed from the official webpage. [1]
* Python 2.7
* Pip: `sudo python -m ensurepip`


#Install
* Clone the repository
* Select the branch you want to work with
    * Exercise 1 is the branch: exercise-1-final
    * Exercise 2 is the branch: exercise-2-final

    
#Run
* Run the `test.sh` script to run all the tests

It should just work* 

*(famous final words)


[1] - Don't install docker from homebrew because you use homebrew in mac you will need docker-machine, which uses VirtualBox to create a virtualmachine and run docker inside. That will make the networking complicated and will prevent you from running the tests properly.