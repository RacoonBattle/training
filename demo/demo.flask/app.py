from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__, static_folder='static')

from sqlalchemy import create_engine
engine = create_engine('sqlite:///:memory:', echo=True)

from sqlalchemy.ext.declarative import declarative_base
Base = declarative_base()

from sqlalchemy import Column, Integer, String, Sequence

class User(Base):
    __tablename__ = 'users'

    id = Column(Integer, Sequence('user_id_seq'), primary_key=True)
    name = Column(String(50))
    fullname = Column(String(50))
    password = Column(String(12))

    def __init__(self, name, fullname, password):
        self.name = name
        self.fullname = fullname
        self.password = password

    def __repr__(self):
        return "<User('%s','%s','%s')>" % (self.name, self.fullname, self.password)

from sqlalchemy.orm import sessionmaker

Session = sessionmaker(bind=engine)
session = Session()

@app.route("/")
def list():
    try:
        users = session.query(User).all()
    except Exception:
        Base.metadata.create_all(engine)
        users = session.query(User).all()

    return render_template('list.html', users=users)

@app.route('/create', methods=['GET', 'POST'])
def create():
    if request.method == 'GET':
        return render_template('create.html')

    name = request.form['name']
    fullname = request.form['fullname']
    password = request.form['password']

    session.add(User(name, fullname, password))

    return redirect(url_for('.list'))

if __name__ == "__main__":
    app.run(debug=True)
