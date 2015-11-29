import re
import pytest

class TestStringCalculator:

    def test_0_for_blank(self):
        assert self.add("") == 0

    def test_1_for_one(self):
        assert self.add("1") == 1

    def test_2_for_one(self):
        assert self.add("2") == 2

    def test_3_for_one_and_two(self):
        assert self.add("1,2") == 3

    def test_8_for_one_and_two_and_four_and_one(self):
        assert self.add("1,2,4,1") == 8

    def test_8_for_one_and_two_and_four_and_one(self):
        assert self.add("1,2\n4,1") == 8

    def test_self_defined_delimiter_are_possible(self):
        assert self.add("//;\n1;2;2;2") == 7

    def test_self_defined_delimiter_are_possible_1(self):
        assert self.add("//-\n1-2-2-2") == 7

    def test_negatives_will_lead_to_an_exception(self):
        with pytest.raises(RuntimeError) as info:
            assert self.add("//;\n1;2;-2;2")

    def add(self, stringToConvert):
        if not stringToConvert:
             return 0

        delimiter = ","
        if stringToConvert.startswith("//"):
            search = re.search("^//(.)", stringToConvert)
            delimiter = search.group(1)
            stringToConvert = stringToConvert.replace("//"+ delimiter + "\n", "")

        stringToConvert = stringToConvert.replace('\n', delimiter)
        splitted_string = [int(i) for i in stringToConvert.split(delimiter)]
        negatives = [i for i in splitted_string if i < 0]
        if negatives:
            raise RuntimeError

        return sum(i for i in splitted_string)
