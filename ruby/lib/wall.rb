# -*- encoding : utf-8 -*-

class Wall
  def self.number_of_bricks(*heights)
    prev_height = [0]
    @brick_count = 0
    heights.each do |height|
      if prev_height.last > height
        prev_height.pop
      end
      if prev_height.last < height || !prev_height.include?(height)
        @brick_count += 1
        prev_height.push height
      end
    end
    @brick_count
  end
end
