local gid = KEYS[1]
local amount = ARGV[1]
local quantity = ARGV[2]

redis.log(redis.LOG_WARNING, "avg=" ..gid, amount, quantity)

redis.call("SET", gid .. "_amount", amount)
redis.call("SET", gid .. "_quantity", quantity)

redis.log(redis.LOG_WARNING, "avg=<<<" ..gid, amount, quantity)

return 1