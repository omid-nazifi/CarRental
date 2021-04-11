1- Download python2.7
https://www.python.org/downloads/

2. install exe
check box add path

pip install -r requirements.txt ((by using pip freeze > requirements.txt))
3. install requirements

install flask
$ pip install Flask
https://flask.palletsprojects.com/en/1.1.x/installation/

4. open the project in Pycharm





-Run the server
	$ export FLASK_APP=hello.py
	$ python -m flask run
	* Running on http://127.0.0.1:5000/
 
or

	$ export FLASK_APP=hello.py
	$ flask run
	 * Running on http://127.0.0.1:5000/
 
-Externally Visible Server:
	$ flask run --host=0.0.0.0
This tells your operating system to listen on all public IPs.

