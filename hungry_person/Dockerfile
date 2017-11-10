FROM python:2.7-alpine
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
ENV FLASK_APP=hungry_person.py
CMD ["flask", "run", "--host=0.0.0.0"]