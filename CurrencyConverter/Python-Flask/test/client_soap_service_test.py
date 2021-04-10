from suds.client import Client as SudsClient
import unittest


class ClientSoapServerTest(unittest.TestCase):
    def test_echo(self):
        url = 'http://127.0.0.1:5000/soap/echo?wsdl'
        client = SudsClient(url=url, cache=None)
        text = client.service.echo()
        self.assertEqual("Welcome to converter server", text)

    def test_convert(self):
        url = 'http://127.0.0.1:5000/soap/convert?wsdl'
        client = SudsClient(url=url, cache=None)
        gbp_price = client.service.convert(20.10, 'GBP')
        self.assertAlmostEqual(14.6162831105, gbp_price)


if __name__ == '__main__':
    unittest.main()
