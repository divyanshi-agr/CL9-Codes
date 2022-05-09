from flask import Flask, jsonify, request

app = Flask(__name__)
num_win = 0
num_loss = 0
num_tie = 0



@app.route('/help', methods = ['GET'])
def help():
	return jsonify({'Routes available': [{'name': 'addwin', 'method': 'GET'},{'name': 'addloss', 'method': 'POST'}, {'name': 'addtie', 'method': 'POST'}, {'name': 'score', 'method': 'GET'}]})


@app.route('/addwin', methods = ['GET'])
def addwin():
	global num_win
	num_win+=1
	return jsonify({'Win Count': num_win})

@app.route('/addloss', methods = ['GET'])
def addloss():
	global num_loss
	num_loss+=1
	return jsonify({'Loss Count': num_loss})

@app.route('/addtie', methods = ['GET'])
def addtie():
	global num_tie
	num_tie+=1
	return jsonify({'Tie Count': num_tie})

@app.route('/score', methods = ['GET'])
def score():
	return jsonify({'Win': num_win, 'Loss': num_loss, 'Tie': num_tie})

# driver function
if __name__ == '__main__':
    app.run(debug = True)