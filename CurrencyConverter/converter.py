from xml.dom import minidom


class Converter:
    CURRENCY_XML_FILENAME = "./resources/eurofxref-daily.xml"
    CURRENCY_USD = 'USD'
    CURRENCY_EUR = 'EUR'

    __price_list = {}

    def __init__(self):
        self.__price_list = self.read_daily_currency_xml()

    def convert(self, price, currency):
        euro_price = self.convert_dollar_to_euro(price)
        if currency == self.CURRENCY_EUR:
            return euro_price

        new_price = self.convert_euro_to_currency(euro_price, currency)
        return new_price

    def convert_dollar_to_euro(self, dollar_price):
        if self.CURRENCY_USD not in self.__price_list or self.__price_list[self.CURRENCY_USD] == 0:
            raise Exception("The rate value of the dollar is non-existent or wrong!")

        return dollar_price / self.__price_list[self.CURRENCY_USD]

    def convert_euro_to_currency(self, euro_price, new_currency):
        if new_currency not in self.__price_list or self.__price_list[new_currency] == 0:
            raise Exception("The rate value of the " + new_currency + " is non-existent or wrong!")
        return euro_price * self.__price_list[new_currency]

    def read_daily_currency_xml(self):
        # parse an xml file by name
        document = minidom.parse(self.CURRENCY_XML_FILENAME)
        items = document.getElementsByTagName('Cube')[0].getElementsByTagName('Cube')[0].getElementsByTagName('Cube')

        prices = {}
        for element in items:
            prices[element.getAttribute('currency')] = float(element.getAttribute('rate'))

        return prices
