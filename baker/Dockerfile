FROM python:3.6-stretch
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
CMD ["python","-m","sanic","baker.app","--host=0.0.0.0","--port=5000","--workers=40"]