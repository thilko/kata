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

    def add(self, stringToConvert):
        if not stringToConvert:
             return 0

        stringToConvert = stringToConvert.replace('\n', ",")
        splitted_string = stringToConvert.split(",")
        return sum([int(i) for i in splitted_string])
