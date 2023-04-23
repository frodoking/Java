local gid = KEYS[1]

local quantity = tonumber(redis.call("GET", gid .. "_quantity") or "0")
if(quantity <= 0 ) then
    return -1
end

local user_id = ARGV[1]
local total_amount = tonumber(redis.call("GET", gid .. "_amount") or "0")
local total_quantity = tonumber(redis.call("GET", gid .. "_quantity") or "0")
local curr_amount = 0
math.randomseed(total_amount)
for i=1,3 do
    curr_amount = math.random(total_amount * 2 / total_quantity)
end

redis.log(redis.LOG_WARNING, "randomseed=" ..gid, total_amount, curr_amount)

redis.call("SADD", gid .. "_result", user_id .. ":" .. curr_amount)
redis.call("SET", gid .. "_amount", total_amount - curr_amount)
redis.call("DECR", gid .. "_quantity")
return curr_amount