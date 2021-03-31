import unittest
from converter import Converter


class ConverterTest(unittest.TestCase):
    def test_convert(self):
        _converter = Converter()
        _dollar = 20

        self.assertAlmostEqual(17.0343241632, _converter.convert(_dollar, 'EUR'))
        self.assertAlmostEqual(33.3157311984, _converter.convert(_dollar, 'BGN'))
        self.assertAlmostEqual(14.543565284, _converter.convert(_dollar, 'GBP'))
        self.assertAlmostEqual(166.595690316, _converter.convert(_dollar, 'TRY'))
        self.assertAlmostEqual(971.211992164, _converter.convert(_dollar, 'PHP'))

    def test_read_daily_currency_xml(self):
        _converter = Converter()
        expected_value = {u'USD': 1.1741, u'IDR': 17063.2, u'BGN': 1.9558, u'ISK': 148.5, u'ILS': 3.9133, u'GBP': 0.85378, u'DKK': 7.4369, u'CAD': 1.4814, u'MXN': 24.2262, u'HUF': 363.3, u'RON': 4.921, u'MYR': 4.8737, u'SEK': 10.2473, u'SGD': 1.5815, u'HKD': 9.1283, u'AUD': 1.5419, u'CHF': 1.1057, u'KRW': 1331.35, u'CNY': 7.7154, u'TRY': 9.78, u'HRK': 7.5698, u'NZD': 1.6794, u'THB': 36.714, u'NOK': 10.0613, u'RUB': 89.1591, u'INR': 86.254, u'JPY': 129.48, u'CZK': 26.122, u'BRL': 6.7685, u'PLN': 4.6582, u'PHP': 57.015, u'ZAR': 17.5396}
        prices = _converter.read_daily_currency_xml()
        self.assertEqual('USD', prices.keys()[0])
        self.assertEqual(expected_value, prices)


if __name__ == '__main__':
    unittest.main()
