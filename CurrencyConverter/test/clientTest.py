from suds.client import Client as SudsClient

url = 'http://127.0.0.1:5000/soap/echo?wsdl'
client = SudsClient(url=url, cache=None)
r = client.service.sum(2, 5)
print(r)
