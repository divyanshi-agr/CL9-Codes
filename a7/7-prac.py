from crypt import methods
from flask import Flask, jsonify, request

app = Flask(__name__)

@app.route('/users', methods=['GET'])
def getAllUsers():
    name = request.args.get('name')
    if name != "":
        return jsonify({'user' : name})
    else:
        return jsonify({'users' : 'true'})

@app.route('/todo', methods=['GET'])
def getToDo()
    name = request.args.get('name')
    return jsonify({'todo' : name})

if __name__ == '__main__':
    app.run(debug = True)



from crypt import methods
from flask import FLask, jsonify, request

app = Flask(__name__)

@app.route('/users', methods = ['GET'])
def getAllUsers():
    name = request.args.get('name')
    if name != "":
        return jsonify({'user': name})
    else:
        return jsonify({'users': 'true'})

@app.route('/todo', methods = ['GET'])
def getTodo():
    name = request.args.get('name')
    return jsonify({'todo': name})

if __name__ == '__main__':
    app.run(debug = True)






























