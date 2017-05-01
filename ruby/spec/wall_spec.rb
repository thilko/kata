# -*- encoding : utf-8

require 'wall'

describe Wall do
  it 'returns 1 brick count' do
    expect(Wall.count_bricks(1)).to eq(1)
  end

  it 'returns 2 bricks for two different heights' do
    expect(Wall.count_bricks(1, 2)).to eq(2)
  end

  it 'returns 2 bricks for three different heights' do
    expect(Wall.count_bricks(1, 2, 1)).to eq(2)
  end

  it 'returns 2 bricks for three different heights' do
    expect(Wall.count_bricks(1, 2, 1, 2)).to eq(3)
  end
end
