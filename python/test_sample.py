import os
import glob

class TestClass:

    def test_native_data_types(self):
        summer = False
        winter = True
        assert (winter and not summer)

    def test_list_comprehensions(self):
        numbers = [3,4,5,6,7,8]
        assert [i+1 for i in numbers] == [4,5,6,7,8,9]

        assert [i for i in numbers if i % 2 == 0] == [4,6,8]

    def test_file_system(self):
        assert os.getcwd() == "/Users/thilko/Documents/projects/python"

    def test_glob(self):
        assert len(glob.glob("*.py")) == 2
