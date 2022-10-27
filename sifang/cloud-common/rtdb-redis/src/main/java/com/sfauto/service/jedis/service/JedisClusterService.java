package com.sfauto.service.jedis.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JedisClusterService {

	/**
	 * 命令将 key 中储存的数字值减一。
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
	 * 本操作的值限制在 64 位(bit)有符号数字表示之内。
	 * 
	 * @param key 对应的key值
	 * @return 执行 DECR 命令之后 key 的值。
	 */
	long decr(String key);

	/**
	 * 命令将 key 中储存的数字值增一。
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
	 * 本操作的值限制在 64 位(bit)有符号数字表示之内。
	 * 
	 * @param key 对应的key值
	 * @return 执行 INCR 命令之后 key 的值。
	 */
	long incr(String key);

	/**
	 * 获取指定 key 的值。如果 key 不存在，返回 nil 。如果key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
	 * 
	 * @param key 指定的key值
	 * @return 返回 key 的值，如果 key 不存在时，返回 nil。 如果 key 不是字符串类型，那么返回一个错误。
	 */
	String get(String key);

	/**
	 * 用于设置给定 key 的值。如果 key 已经存储其他值， set就覆写旧值，且无视类型。
	 * 
	 * @param key 指定的key值
	 * @param value set在设置操作成功完成后，返回 OK 。
	 */
	void set(String key, String value);

	/**
	 * 用于设置给定 key 的值。如果 key 已经存储其他值， set就覆写旧值，且无视类型。
	 *
	 * @param key 指定的key值
	 * @param value set在设置操作成功完成后，返回 OK 。
	 * @param ex 设置键的过期时间为 second 秒。
	 */
	void set(String key, String value, int ex);

	/**
	 * 命令用于设置指定 key 的值，并返回 key 的旧值
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	String getset(String key, String value);

	/**
	 * 命令用于设置指定 key 的值，并返回 key 的旧值
	 *
	 * @param key
	 * @param value
	 * @param ex
	 * @return
	 */
	String getset(String key, String value, int ex);

	/**
	 * 删除已存在的键。不存在的 key 会被忽略。
	 *
	 * @param keys 删除给定的一个或多个 key, 不存在的 key 会被忽略。
	 * @return 被删除 key 的数量。
	 */
	long del(String... keys);

	/**
	 * 检查给定 key 是否存在。
	 * @param key 给定的键值
	 * @return 若 key 存在返回 true ，否则返回 false 。
	 */
	boolean exists(String key);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时，它会被自动删除。
	 *
	 * @param key 键值
	 * @param seconds 过期时间，单位为秒
	 * @return 设置成功返回 1 。 当 key 不存在或者不能为 key 设置过期时间时(比如在低于 2.1.3 版本的 Redis 中你尝试更新 key 的过期时间)返回 0 。
	 */
	long expire(String key, int seconds);

	/**
	 * <p>同时设置一个或多个 key-value 对</p>
	 * <p>example:</p>
	 * <p>  obj.mset(new String[]{"key2","value1","key2","value2"})</p>
	 * @param keyValueMap 一个或多个 key-value 对
	 * @param expire 过期时间，单位为秒
	 * @return 总是返回 OK。
	 *
	 */
	String mset(Map<String, String> keyValueMap, int expire);

	/**
	 * <p>返回所有(一个或多个)给定 key 的值。</p>
	 * <p>example:</p>
	 * <p>  obj.mget(new String[]{"key2", "key2"})</p>
	 * @param keys 一个或多个 key 对
	 * @return 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil 。因此，该命令永不失败。
	 *
	 */
	Map<String, String> mget(List<String> keys);

/************************* Redis哈希(Hash)相关操作 *********************************/
	/**
	 * 删除hash表 key 中的一个或多个指定域，不存在的域将被忽略
	 *
	 * @param key 键值
	 * @param field 一个或多个指定字段
	 *
	 * @return 被成功删除字段的数量，不包括被忽略的字段。
	 */
	long hdel(String key, String... field);

	/**
	 * 查看哈希表的指定域是否存在。
	 * @param key 给定的键值
	 * @param field 给定的字段
	 * @return 如果hash表含有给定字段，返回true 。 如果hash表不含给定字段，或key 不存在，返回false 。
	 */
	boolean hexists(String key, String field);

	/**
	 * 返回hash表key中给定域field的值。
	 *
	 * @param key 哈希键值
	 * @param field 字段名
	 * @return 返回给定字段的值。如果给定的字段或 key 不存在时，返回 null。
	 */
	String hget(String key, String field);

	/**
	 * 返回hash表key中，所有的域和值。
	 *
	 * @param key hash键值
	 * @return 以哈希表形式返回hash表的域和域的值。 若 key 不存在，返回空哈希表。
	 */
	Map<String, String> hgetall(String key);

	/**
	 * 用于获取hash表中的所有字段名。
	 *
	 * @param key 哈希键值
	 * @return 包含哈希表中所有字段的列表。 当 key 不存在时，返回一个空列表。
	 */
	Set<String> hkeys(String key);

	/**
	 * 用于获取hash表中字段的数量。
	 *
	 * @param key 哈希键值
	 * @return 哈希表中字段的数量。 当 key 不存在时，返回 0 。
	 */
	long hlen(String key);

	/**
	 * 用于返回hash表中，一个或多个给定字段的值。
	 * 如果指定的字段不存在于哈希表，那么返回一个 null 值。
	 *
	 * @param key 哈希键值
	 * @param fields 一个或多个字段
	 * @return 一个包含多个给定字段关联值的表，表值的排列顺序和指定字段的请求顺序一样。
	 */
	List<String> hmget(String key, String... fields);

	/**
	 * 用于同时将多个 field-value (字段-值)对设置到hash表中。
	 * 此命令会覆盖哈希表中已存在的字段。
	 * 如果hash表不存在，会创建一个空hash表，并执行 HMSET 操作。
	 *
	 * @param key 哈希键值
	 * @param hashmap  field-value (字段-值)哈希表
	 * @return true:命令执行成功;false: 命令执行失败;
	 */
	boolean hmset(String key, Map<String, String> hashmap);

	/**
	 * 用于为哈希表中的字段赋值 。
	 * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
	 * 如果字段已经存在于哈希表中，旧值将被覆盖。
	 *
	 * @param key 哈希键值
	 * @param field 字段名
	 * @param value 要设置的新值
	 * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
	 */
	long hset(String key, String field, String value);

	/**
	 * <p>将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
	 * 若域 field 已经存在，该操作无效。
	 * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。</p>
	 *
	 * @param key 哈希键值
	 * @param field 字段名
	 * @param value 设定值
	 * @return 设置成功，返回 1 。 如果给定字段已经存在且没有操作被执行，返回 0 。
	 */
	long hsetnx(String key, String field, String value);

	/**
	 * 用于为哈希表中的字段赋值 并设定键值过期时间。功能为hset+expire。
	 *
	 * @param key 哈希键值
	 * @param field 字段名
	 * @param value 设定值
	 * @param liveTime 过期时间，单位为秒
	 * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
	 */
	long hsetex(String key, String field, String value, int liveTime);

	
/************************* Redis集合(Set)相关操作 *********************************/
	/**
	 * 将一个或多个成员元素加入到集合中，已经存在于集合的成员元素将被忽略。
	 * 假如集合 key 不存在，则创建一个只包含添加的元素作成员的集合。
	 * 当集合 key 不是集合类型时，返回一个错误。
	 * 
	 * @param key 集合键值
	 * @param members 要添加的成员元素
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
	 */
	long sadd(String key, String... members);
	
	/**
	 * 返回集合中 key 元素的数量。
	 * 
	 * @param key 集合键值
	 * @return 集合的数量。 当集合 key 不存在时，返回 0 。
	 */
	long scard(String key);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。
	 * 不存在的 key 被视为空集。
	 * 
	 * @param keys 单个key或key数组
	 * @return 包含差集成员的列表。
	 */
	Set<String> sdiff(String... keys);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的交集。
	 * 不存在的 key 被视为空集。	 *
	 * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
	 * 
	 * @param keys 单个key或key数组
	 * @return 交集成员的列表。
	 */
	Set<String> sinter(String... keys);

	/**
	 * 返回给定集合的并集。不存在的集合 key 被视为空集。
	 *
	 * @param keys 一个或多个集合键值
	 * @return 并集成员的列表。
	 */
	Set<String> sunion(String... keys);

	/**
	 * 判断成员元素是否是集合的成员。
	 * 
	 * @param key 集合键值
	 * @param member 成员元素
	 * @return 如果成员元素是集合的成员，返回 true，否则返回false。
	 */
	boolean sismember(String key, String member);
	
	/**
	 * 返回集合 key 中的所有成员。
	 * 不存在的 key 被视为空集合。
	 * 
	 * @param key 集合键值
	 * @return 集合中的所有成员。
	 */
	Set<String> smembers(String key);
	
	/**
	 * 将指定成员 member 元素从 source 集合移动到 destination 集合。
	 * SMOVE 是原子性操作。
	 * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
	 * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
	 * 当 source 或 destination 不是集合类型时，返回一个错误。
	 * 
	 * @param srckey 源集合键值
	 * @param dstkey 目标集合键值
	 * @param member 成员元素
	 * @return 如果成员元素被成功移除，返回 1 。 如果成员元素不是 source 集合的成员，并且没有任何操作对 destination 集合执行，那么返回 0 。
	 */
	long smove(String srckey, String dstkey, String member);

	/**
	 * 用于移除并返回集合中的一个随机元素。
	 * 
	 * @param key 集合键值
	 * @return 被移除的随机元素。 当集合不存在或是空集时，返回 null。
	 */
	String spop(String key);

	/**
	 * 用于移除并返回集合中的指定数量的随机元素。
	 * 
	 * @param key 集合键值
	 * @param count 指定元素个数
	 * @return 被移除的随机元素。 当集合不存在或是空集时，返回 null。
	 */
	Set<String> spop(String key, int count);

	/**
	 * 用于移除集合中的一个或多个成员元素，不存在的成员元素会被忽略。
	 * 当 key 不是集合类型，返回一个错误。
	 * 
	 * @param key 集合键值
	 * @param members 一个或多个成员元素
	 * @return 被成功移除的元素的数量，不包括被忽略的元素。
	 */
	long srem(String key, String... members);
	
/************************* Redis集合(List)相关操作 *********************************/

	/**
	 * 将一个或多个值插入到list头部。 如果 key 不存在，一个空list会被创建并执行 LPUSH 操作。 当 key 存在但不是list类型时，返回一个错误。
	 *
	 * @param key 插入的key值
	 * @param value 待插入的一个或多个值
	 * @return 执行 LPUSH 命令后，list的长度。
	 */
	long lpush(String key, String... value);

	/**
	 * 移除并返回list的第一个元素。
	 *
	 * @param key 对应的key值
	 * @return 移除并返回list的第一个元素。 当list不存在时，返回 nil 。
	 */
	String lpop(String key);

	/**
	 * 将一个或多个值插入到list的尾部(最右边)。 如果list不存在，一个空list会被创建并执行 RPUSH 操作。 当key存在但不是list类型时，返回一个错误。
	 *
	 * @param key 插入的key值
	 * @param value 待插入的一个或多个值
	 * @return 执行 RPUSH 操作后，list的长度。
	 */
	long rpush(String key, String... value);

	/**
	 * 移除并返回list的最后一个元素。
	 *
	 * @param key 对应的key值
	 * @return list的最后一个元素。 当key不存在时，返回 nil 。
	 */
	String rpop(String key);

	ScanResult<byte[]> sscan(byte[] encode, byte[] encode1, ScanParams params);
}
