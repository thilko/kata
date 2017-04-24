# -*- encoding : utf-8 -*-
require 'spec_helper'
require 'wall'

describe Wall do
  it 'returns brick count 1 if one height is given' do
    expect(Wall.number_of_bricks(1)).to eq(1)
  end

  it 'returns brick count 2 if two different heights are given' do
    expect(Wall.number_of_bricks(2,4)).to eq(2)
  end

  it 'returns brick count 2 if height is going back to known height' do
    expect(Wall.number_of_bricks(2,4,2)).to eq(2)
  end

  it 'returns the correct brick count for chainsaw pattern' do
    expect(Wall.number_of_bricks(2,4,2,4,2,4)).to eq(4)
  end

  it 'returns correct brick count if height is repeating' do
    expect(Wall.number_of_bricks(2,4,4,2,2,4,2,4)).to eq(4)
  end

  it 'returns correct brick count if a new height is introduced' do
    expect(Wall.number_of_bricks(2,6,10,8)).to eq(4)
  end

  it 'returns correct brick count for a tower' do
    expect(Wall.number_of_bricks(4,10,2,10,5,4)).to eq(6)
  end
end

