#include "gtest/gtest.h"
#include "sample_test.h"
#include <chrono>
#include <algorithm>

using namespace std::chrono;

enum Type { spo2, temp };

class Measurement{

    system_clock::time_point _start;
    double _value;
    Type _type;

  public:
    Measurement(system_clock::time_point start, double value, Type type) : _start{start}, _value{value}, _type{type} {}
    system_clock::time_point getStart() const { return _start; }
    void start(system_clock::time_point start){_start = start;};
    
    void type(Type t){_type = t;}
    Type type(){return _type;}
    double value(){ return _value; }
    void value(double value){_value = value; };

};

using Measurements = std::vector<Measurement>;


Measurements sample(system_clock::time_point start, Measurements measurements)
{
  auto lmbd = [](const Measurement& a, const Measurement& b){ return a.getStart() < b.getStart(); };
  std::sort(measurements.begin(), measurements.end(), lmbd);

  auto result = Measurements();
  auto sampleRangeDate = start;
  auto current = std::vector<Measurement>();
  for(Measurement m: measurements){
    if(m.getStart() > sampleRangeDate + minutes(5)){
      sampleRangeDate = sampleRangeDate + minutes(5);
      result.push_back({sampleRangeDate, current.back().value(), current.back().type()});
    }
    current.push_back(m);
  }
  
  result.push_back({sampleRangeDate + minutes(5), current.back().value(), current.back().type()});

  return result;
}

TEST_F(SampleTest, ReturnedMeasurementIsSampledToTheEnd){
  system_clock::time_point start = system_clock::now();
  auto sampled = sample(start, {{start, 45.8, spo2}});

  ASSERT_EQ(sampled[0].getStart(), start + minutes(5));
}

TEST_F(SampleTest, TheLastMeasurementIsReturned){
  system_clock::time_point start = system_clock::time_point{hours{13} + minutes{10}};
  auto sampled = sample(start, {{start + minutes(2), 45.3, spo2 }, {start + minutes(3), 123.9, spo2}});

  ASSERT_EQ(sampled[0].getStart(), start + minutes(5));
  ASSERT_EQ(sampled[0].value(), 123.9);
}

TEST_F(SampleTest, MeasurementsInTwoSampleRanges){
  system_clock::time_point start = system_clock::time_point{hours{13} + minutes{10}};
  auto sampled = sample(start, {{start + minutes(2), 45.3, spo2 }, {start + minutes(6), 123.9, spo2}});

  ASSERT_EQ(sampled[0].getStart(), start + minutes(5));
  ASSERT_EQ(sampled[0].value(), 45.3);

  ASSERT_EQ(sampled[1].getStart(), start + minutes(10));
  ASSERT_EQ(sampled[1].value(), 123.9);
}

TEST_F(SampleTest, MeasurementsInThreeSampleRanges){
  system_clock::time_point start = system_clock::time_point{hours{13} + minutes{10}};
  auto sampled = sample(start, {  {start + minutes(2), 45.3, spo2}, 
                                  {start + minutes(6), 123.9, spo2}, 
                                  {start + minutes(11), 42.8, spo2},
                                  {start + minutes(12), 99.8, spo2}
                                }
  );

  ASSERT_EQ(sampled[0].getStart(), start + minutes(5));
  ASSERT_EQ(sampled[0].value(), 45.3);
  ASSERT_EQ(sampled[0].type(), spo2);

  ASSERT_EQ(sampled[1].getStart(), start + minutes(10));
  ASSERT_EQ(sampled[1].value(), 123.9);
  ASSERT_EQ(sampled[0].type(), spo2);

  ASSERT_EQ(sampled[2].getStart(), start + minutes(15));
  ASSERT_EQ(sampled[2].value(), 99.8);
  ASSERT_EQ(sampled[0].type(), spo2);
}

TEST_F(SampleTest, MeasurementsWithDifferentTypesAreSampledSeperately){
  system_clock::time_point start = system_clock::time_point{hours{13} + minutes{10}};
  auto sampled = sample(start, {{start + minutes(2), 45.3, temp }, {start + minutes(3), 123.9, spo2}});

  ASSERT_EQ(sampled[0].getStart(), start + minutes(5));
  ASSERT_EQ(sampled[0].value(), 45.3);
  ASSERT_EQ(sampled[0].type(), temp);

  ASSERT_EQ(sampled[1].getStart(), start + minutes(5));
  ASSERT_EQ(sampled[1].value(), 123.9);
  ASSERT_EQ(sampled[0].type(), spo2);
}

int main(int argc, char **argv) {
 ::testing::InitGoogleTest(&argc, argv);
 return RUN_ALL_TESTS();
}
