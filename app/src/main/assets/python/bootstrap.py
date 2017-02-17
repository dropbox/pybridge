"""
 This file is executed when the Python interpreter is started.
 Use this file to configure all your necessary python code.

"""

import android_log_redirect
import json


def router(args):
    """
    Defines the router function that routes by function name.

    :param args: JSON arguments
    :return: JSON response
    """
    values = json.loads(args)

    ex = None
    function = None
    try:
        function = routes[values.get('function')]

        status = 'ok'
        res = function(values)
    except KeyError as e:
        status = 'fail'
        res = None
        ex = repr(e)

    #print("Routed command: ", function, res)
    return json.dumps({
        'status': status,
        'result': res,
        'ex': ex,
    })


def greet(args):
    """Simple function that greets someone."""
    return 'Hello %s' % args['name']


def add(args):
    """Simple function to add two numbers."""
    return args['a'] + args['b']


def mul(args):
    """Simple function to multiply two numbers."""
    return args['a'] * args['b']


routes = {
    'greet': greet,
    'add': add,
    'mul': mul,
}

#print("bootstrap.py init completed")
