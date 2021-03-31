# python lib
import os
# flask lib
from flaskext.enterprise import Enterprise
from flask import Flask, render_template
# internal classes
from converter import Converter

# config Flask
app = Flask(__name__)

# config Flask Enterprise
enterprise = Enterprise(app)
String = enterprise._sp.String
Double = enterprise._sp.Double


class Service(enterprise.SOAPService):
    """ Soap Service Class """
    __soap_target_namespace__ = 'CurrencyConverter'
    __soap_server_address__ = '/soap'

    @enterprise.soap(Double, String, _returns=Double)
    def convert(self, dollar, currency):
        """ Function to convert dollar to given currency
        Args:
            dollar : double
            currency : string

        Returns:
            return a double
        """

        converter = Converter()
        converted_price = converter.convert(dollar, currency)
        return converted_price

    @enterprise.soap(_returns=String)
    def echo(self):
        """ Function to echo something. It is just for test soap service by clients

        Returns:
            return a text
        """
        return "Welcome to converter server"


if __name__ == '__main__':
    # Bind to PORT if defined, otherwise default to 5000.
    port = int(os.environ.get('PORT', 5000))
    app.debug = True
    app.run(host='0.0.0.0', port=port)
