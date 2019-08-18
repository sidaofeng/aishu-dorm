package com.waken.dorm.common.utils.redis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisListCommands.Position;
import org.springframework.data.redis.connection.RedisZSetCommands.Tuple;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @ClassName RedisCacheManager
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/21 19:56
 **/
@Component
@Lazy(false)
public class RedisCacheManager implements InitializingBean, DisposableBean {

    //    private static Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);
    @Resource
    public RedisTemplate<Object, ?> redisTemplate;
    private RedisSerializer<Object> serializer;

    // ======================String 相关操作========================

    /**
     * 将字符串值 value 关联到 key, 将字符串值 value 关联到 key 。如果 key 已经持有其他值， SET 就覆写旧值，无视类型。
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean set(final Object key, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    connection.set(keys, values);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
     * <p>
     * 如果 key 已经存在， SETEX 命令将覆写旧值。
     *
     * @param key
     * @param value
     * @param t
     * @return
     */
    public Boolean setEx(final Object key, final Object value, final Long t) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    connection.setEx(keys, t, values);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。
     *
     * @param key
     * @param value
     * @param t
     * @return
     */
    public Boolean pSetEx(final Object key, final Object value, final Long t) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    connection.pSetEx(keys, t, values);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * <p>
     * 若给定的 key 已经存在，则 SETNX 不做任何动作。
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setNx(final Object key, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    connection.setNX(keys, values);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 同时设置一个或多个 key-value 对。
     *
     * @param tuple
     * @param overwrite 是否覆盖，true：覆盖,false:不覆盖
     * @return
     */
    public Boolean mSet(final Map<Object, Object> tuple, final boolean overwrite) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    if (null == tuple || tuple.isEmpty()) {
                        return false;
                    }
                    Map<byte[], byte[]> t = Maps.newHashMap();
                    for (Entry<Object, Object> e : tuple.entrySet()) {
                        t.put(serializer.serialize(e.getKey()),
                                serializer.serialize(e.getValue()));
                    }
                    if (overwrite) {
                        connection.mSet(t);
                    } else {
                        connection.mSetNX(t);
                    }
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 返回 key 所关联的字符串值。
     *
     * @param key
     * @return
     */
    public Object get(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = connection.get(keys);
                    if (values == null) {
                        return null;
                    }
                    return serializer.deserialize(values);
                }
            });
        }
        return null;
    }

    public Object getString(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = connection.get(keys);
                    if (values == null) {
                        return null;
                    }
                    return new String(values);
                }
            });
        }
        return null;
    }

    /**
     * 返回所有(一个或多个)给定 key 的值。
     *
     * @param keys
     * @return
     */
    public Map<Object, Object> mGet(final Object... keys) {
        if (redisTemplate != null) {
            return redisTemplate
                    .execute(new RedisCallback<Map<Object, Object>>() {
                        public Map<Object, Object> doInRedis(
                                RedisConnection connection)
                                throws DataAccessException {
                            final byte[][] rawKeys = new byte[keys.length][];

                            int i = 0;
                            for (Object key : keys) {
                                rawKeys[i++] = serializer.serialize(key);
                            }
                            List<byte[]> re = connection.mGet(rawKeys);
                            if (null == re || re.isEmpty()) {
                                return Maps.newHashMap();
                            }
                            Map<Object, Object> result = Maps.newHashMap();
                            int j = 0;
                            for (byte[] b : re) {
                                result.put(keys[j++], serializer.deserialize(b));
                            }
                            return result;
                        }
                    });
        }
        return null;
    }

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
     * <p>
     * 位的设置或清除取决于 value 参数，可以是 0 也可以是 1 。
     * <p>
     * 当 key 不存在时，自动生成一个新的字符串值。
     *
     * @param key
     * @param offset
     * @param value
     * @return
     */
    public Boolean setBit(final Object key, final long offset,
                          final boolean value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    connection.setBit(keys, offset, value);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
     * <p>
     * 当 offset 比字符串值的长度大，或者 key 不存在时，返回 0 。
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean getBit(final Object key, final long value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.getBit(keys, value);
                }
            });
        }
        return false;
    }

    /**
     * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。
     * <p>
     * 不存在的 key 当作空白字符串处理。
     *
     * @param key
     * @param offset
     * @param value
     * @return
     */
    public Boolean setRange(final Object key, final Long offset,
                            final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    connection.setRange(keys, values, offset);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * <p>
     * 负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return
     */
    public byte[] getRange(final Object key, final long startOffset,
                           final long endOffset) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<byte[]>() {
                public byte[] doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.getRange(keys, startOffset, endOffset);
                }
            });
        }
        return null;
    }

    /**
     * 将 key 所储存的值减去减量 decrement 。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。
     *
     * @param key
     * @param integer
     * @return
     */
    public Long decrBy(final Object key, final long integer) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.decrBy(keys, integer);
                }
            });
        }
        return null;
    }

    /**
     * 将 key 中储存的数字值减一。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
     *
     * @param key
     * @return
     */
    public Long decr(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.decr(keys);
                }
            });
        }
        return null;
    }

    /**
     * 将 key 所储存的值加上增量 increment 。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     *
     * @param key
     * @param integer
     * @return
     */
    public Long incrBy(final Object key, final long integer) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.incrBy(keys, integer);
                }
            });
        }
        return null;
    }

    /**
     * 将 key 中储存的数字值增一。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     *
     * @param key
     * @return
     */
    public Long incr(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.incr(keys);
                }
            });
        }
        return null;
    }

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * <p>
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     *
     * @param key
     * @param value
     * @return
     */
    public Long append(final Object key, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    return connection.append(keys, values);
                }
            });
        }
        return null;
    }

    // ======================================String相关操作结束=================================
    // =====================================key相关操作=========================================

    /**
     * 删除给定的一个或多个 key 。不存在的 key 会被忽略。
     *
     * @param keys
     * @return
     */
    public Long del(final Object... keys) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    final byte[][] rawKeys = new byte[keys.length][];
                    int i = 0;
                    for (Object key : keys) {
                        rawKeys[i++] = serializer.serialize(key);
                    }
                    return connection.del(rawKeys);
                }
            });
        }
        return null;
    }

    /**
     * 检查给定 key 是否存在。
     *
     * @param key
     * @return
     */
    public Boolean exists(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.exists(keys);
                }
            });
        }
        return false;
    }

    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     *
     * @param key
     * @param value 秒
     * @return
     */
    public Boolean expire(final Object key, final long value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.expire(keys, value);
                }
            });
        }
        return false;
    }

    /**
     * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。
     * <p>
     * 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean expireAt(final Object key, final long value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.expireAt(keys, value);
                }
            });
        }
        return false;
    }

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @param value
     * @return
     */
    public Long ttl(final Object key, final long value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.ttl(keys);
                }
            });
        }
        return 0l;
    }

    /**
     * 返回 key 所储存的值的类型。
     *
     * @param key
     * @return
     */
    public DataType type(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<DataType>() {
                public DataType doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.type(keys);
                }
            });
        }
        return null;
    }

    /**
     * 删除对象 ,依赖key
     */
    public void delete(Object key) {
        List<Object> list = Lists.newArrayList();
        list.add(key);
        delete(list);
    }

    /**
     * 删除集合 ,依赖key集合
     */
    private void delete(List<Object> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。
     *
     * @param key
     * @param params
     * @return
     */
    public List<Object> sort(final Object key, final SortParameters params) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<List<Object>>() {
                public List<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    List<Object> data = Lists.newArrayList();
                    List<byte[]> re = connection.sort(keys, params);
                    for (byte[] by : re) {
                        data.add(serializer.deserialize(by));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    // ================key相关操作结束====================
    // =================Hash相关操作===================

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * <p>
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * <p>
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Boolean hSet(final Object key, final Object field, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] fields = serializer.serialize(field);
                    byte[] values = serializer.serialize(value);
                    return connection.hSet(keys, fields, values);
                }
            });
        }
        return null;
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @param key
     * @param field
     * @return
     */
    public Object hGet(final Object key, final Object field) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] fields = serializer.serialize(field);
                    return serializer.deserialize(connection.hGet(keys, fields));
                }
            });
        }
        return null;
    }

    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * <p>
     * 若域 field 已经存在，该操作无效。
     * <p>
     * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Boolean hSetNX(final Object key, final Object field,
                          final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] fields = serializer.serialize(field);
                    byte[] values = serializer.serialize(value);
                    return connection.hSetNX(keys, fields, values);
                }
            });
        }
        return null;
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * <p>
     * 此命令会覆盖哈希表中已存在的域。
     * <p>
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param key
     * @param hash
     * @return
     */
    public Boolean hMSet(final Object key, final Map<byte[], byte[]> hash) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    connection.hMSet(keys, hash);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 返回哈希表 key 中，一个或多个给定域的值
     *
     * @param key
     * @param fields
     * @return
     */
    public List<Object> hMGet(final Object key, final byte[]... fields) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<List<Object>>() {
                public List<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    List<Object> data = new ArrayList<Object>();
                    byte[] keys = serializer.serialize(key);
                    List<byte[]> re = connection.hMGet(keys, fields);
                    for (byte[] by : re) {
                        data.add(serializer.deserialize(by));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     * <p>
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * <p>
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * <p>
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     * <p>
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     * <p>
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hIncrBy(final Object key, final Object field, final long value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] fields = serializer.serialize(field);
                    return connection.hIncrBy(keys, fields, value);
                }
            });
        }
        return null;
    }

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(final Object key, final Object field) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] fields = serializer.serialize(field);
                    return connection.hExists(keys, fields);
                }
            });
        }
        return null;
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     */
    public Long hDel(final Object key, final Object... fields) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    final byte[][] rawKeys = new byte[fields.length][];
                    int i = 0;
                    for (Object f : fields) {
                        rawKeys[i++] = serializer.serialize(f);
                    }
                    return connection.hDel(keys, rawKeys);
                }
            });
        }
        return null;
    }

    /**
     * 返回哈希表 key 中域的数量。
     *
     * @param key
     * @return
     */
    public Long hlen(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.hLen(keys);
                }
            });
        }
        return null;
    }

    /**
     * 返回哈希表 key 中的所有域。
     *
     * @param key
     * @return
     */
    public Set<Object> hKeys(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<Object> data = Sets.newHashSet();
                    Set<byte[]> re = connection.hKeys(keys);
                    for (byte[] by : re) {
                        data.add(serializer.deserialize(by));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * 返回哈希表 key 中所有域的值。
     *
     * @param key
     * @return
     */
    public List<Object> hVals(final String key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<List<Object>>() {
                public List<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    List<Object> data = Lists.newArrayList();
                    List<byte[]> re = connection.hVals(keys);
                    for (byte[] by : re) {
                        data.add(serializer.deserialize(by));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * 返回哈希表 key 中，所有的域和值。
     * <p>
     * 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hGetAll(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate
                    .execute(new RedisCallback<Map<Object, Object>>() {
                        public Map<Object, Object> doInRedis(
                                RedisConnection connection)
                                throws DataAccessException {
                            byte[] keys = serializer.serialize(key);
                            Map<Object, Object> ret = Maps.newHashMap();
                            Map<byte[], byte[]> jret = connection.hGetAll(keys);
                            for (Entry<byte[], byte[]> entry : jret
                                    .entrySet()) {
                                ret.put(serializer.deserialize(entry.getKey()),
                                        serializer.deserialize(entry.getValue()));
                            }
                            return ret;

                        }
                    });
        }
        return null;
    }

    /**
     * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。
     * <p>
     * 当 pivot 不存在于列表 key 时，不执行任何操作。
     * <p>
     * 当 key 不存在时， key 被视为空列表，不执行任何操作。
     *
     * @param key
     * @param where
     * @param pivot
     * @param value
     * @return
     */
    public Long linsert(final Object key, final Position where,
                        final Object pivot, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] pivots = serializer.serialize(pivot);
                    byte[] values = serializer.serialize(value);
                    return connection.lInsert(keys, where, pivots, values);
                }
            });
        }
        return null;
    }

    // =================Hash相关操作结束===================
    // =================List相关操作===================

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Long lrem(final Object key, final long count, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    return connection.lRem(keys, count, values);
                }
            });
        }
        return null;
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param key
     * @param value
     * @return
     */
    public Long lpush(final Object key, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    return connection.lPush(keys, values);
                }
            });
        }
        return null;
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * <p>
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist
     * a b c ，得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH
     * mylist c 。
     * <p>
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
     *
     * @param key
     * @param value
     * @return
     */
    public Long rPush(final Object key, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    return connection.rPush(keys, values);

                }
            });
        }
        return null;
    }

    /**
     * 返回列表 key 的长度。
     * <p>
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     *
     * @param key
     * @return
     */
    public Long lLen(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.lLen(keys);
                }
            });
        }
        return null;
    }

    /**
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * <p>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * <p>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> lRange(final Object key, final Long start,
                               final Long end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<List<Object>>() {
                public List<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    List<Object> data = Lists.newArrayList();
                    List<byte[]> re = connection.lRange(keys, start, end);
                    for (byte[] by : re) {
                        data.add(serializer.deserialize(by));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * <p>
     * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。
     * <p>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * <p>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Boolean ltrim(final Object key, final long start, final long end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    connection.lTrim(keys, start, end);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 返回列表 key 中，下标为 index 的元素。
     * <p>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * <p>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param index
     * @return
     */
    public Object lIndex(final Object key, final long index) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] re = connection.lIndex(keys, index);
                    return serializer.deserialize(re);
                }
            });
        }
        return null;
    }

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value 。
     * <p>
     * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
     *
     * @param key
     * @param index
     * @param value
     * @return
     */
    public Boolean lSet(final Object key, final long index, final Object value) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] values = serializer.serialize(value);
                    connection.lSet(keys, index, values);
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 移除并返回列表 key 的头元素。
     *
     * @param key
     * @return
     */
    public Object lPop(final Object key) {
        if (redisTemplate != null) {
            redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] re = connection.lPop(keys);
                    return serializer.deserialize(re);
                }
            });
        }
        return null;
    }

    /**
     * 移除并返回列表 key 的头元素。
     *
     * @param key
     * @return
     */
    public Object bRPop(final Object key, final int timeOut) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keySerialize = serializer.serialize(key);
                    List<byte[]> reList = connection.bRPop(timeOut, keySerialize);
                    if (!reList.isEmpty()) {
                        return serializer.deserialize(reList.get(1));
                    }
                    return null;
                }
            });
        }
        return null;
    }

    /**
     * 移除并返回列表 key 的尾元素。
     *
     * @param key
     * @return
     */
    public Object rPop(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] re = connection.rPop(keys);
                    return serializer.deserialize(re);
                }
            });
        }
        return null;
    }

    // =================List相关操作结束===================
    // =================Set相关操作===================

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     * <p>
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     *
     * @param key
     * @param member
     * @return
     */
    public Long sAdd(final Object key, final Object member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.sAdd(keys, members);
                }
            });
        }
        return null;
    }

    /**
     * 返回集合 key 中的所有成员。
     * <p>
     * 不存在的 key 被视为空集合。
     *
     * @param key
     * @return
     */
    public Set<Object> sMembers(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.sMembers(keys);
                    Set<Object> data = Sets.newHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     *
     * @param key
     * @param member
     * @return
     */
    public Long sRem(final Object key, final Object member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.sRem(keys, members);
                }
            });
        }
        return null;
    }

    /**
     * 移除并返回集合中的一个随机元素
     *
     * @param key
     * @return
     */
    public Object sPop(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] re = connection.sPop(keys);
                    return serializer.deserialize(re);
                }
            });
        }
        return null;
    }

    /**
     * 返回集合 key 的基数(集合中元素的数量)。
     *
     * @param key
     * @return
     */
    public Long sCard(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.sCard(keys);
                }
            });
        }
        return null;
    }

    /**
     * 判断 member 元素是否集合 key 的成员。
     *
     * @param key
     * @param member
     * @return
     */
    public Boolean sIsMember(final Object key, final String member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.sIsMember(keys, members);
                }
            });
        }
        return null;
    }

    /**
     * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。
     *
     * @param key
     * @return
     */
    public Object sRandMember(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] re = connection.sRandMember(keys);
                    return serializer.deserialize(re);
                }
            });
        }
        return null;
    }

    // =================Set相关操作结束===================
    // =================SortSet相关操作===================

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     * <p>
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该
     * member 在正确的位置上。
     * <p>
     * score 值可以是整数值或双精度浮点数。
     * <p>
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Boolean zAdd(final Object key, final double score,
                        final Object member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.zAdd(keys, score, members);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * <p>
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     * <p>
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> zRange(final Object key, final int start, final int end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRange(keys, start, end);
//                    Set<Object> data = Sets.newHashSet();
                    Set<Object> data = Sets.newLinkedHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     *
     * @param key
     * @param member
     * @return
     */
    public Long zRem(final Object key, final Object member) {
        if (redisTemplate != null) {
            redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.zRem(keys, members);
                }
            });
        }
        return null;
    }

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。
     * <p>
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让
     * member 的 score 值减去 5 。
     * <p>
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key
     * increment member 。
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Double zIncrBy(final Object key, final double score,
                          final Object member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Double>() {
                public Double doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.zIncrBy(keys, score, members);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
     * <p>
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
     *
     * @param key
     * @param member
     * @return
     */
    public Long zRank(final Object key, final Object member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.zRank(keys, members);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
     * <p>
     * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
     *
     * @param key
     * @param member
     * @return
     */
    public Long zRevRank(final Object key, final Object member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.zRevRank(keys, members);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * <p>
     * <p>
     * 其中成员的位置按 score 值递减(从大到小)来排列。
     * <p>
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order)排列。
     * <p>
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> zRevRange(final Object key, final int start,
                                 final int end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRevRange(keys, start, end);
                    Set<Object> data = Sets.newLinkedHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Tuple> zRangeWithScores(final Object key, final int start,
                                       final int end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRangeWithScores(keys, start, end);
                }
            });
        }
        return null;
    }

    public Set<Tuple> zRevRangeWithScores(final Object key, final int start,
                                          final int end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRevRangeWithScores(keys, start, end);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 的基数。
     *
     * @param key
     * @return
     */
    public Long zCard(final Object key) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zCard(keys);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中，成员 member 的 score 值。
     *
     * @param key
     * @param member
     * @return
     */
    public Double zScore(final Object key, final Object member) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Double>() {
                public Double doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    byte[] members = serializer.serialize(member);
                    return connection.zScore(keys, members);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zCount(final Object key, final double min, final double max) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zCount(keys, min, max);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score
     * 值递减(从大到小)的次序排列。
     * <p>
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public Set<Object> zrevrangeByScore(final Object key, final double max,
                                        final double min) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRevRangeByScore(keys, max,
                            min);
                    Set<Object> data = Sets.newHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    public Set<Object> zrevrangeByScore(final Object key, final double max,
                                        final double min, final int offset, final int count) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRevRangeByScore(keys, min,
                            max, offset, count);
                    Set<Object> data = Sets.newHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    /**
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<Tuple> zrangeByScoreWithScores(final Object key,
                                              final double min, final double max) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRangeByScoreWithScores(keys, min, max);
                }
            });
        }
        return null;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final Object key,
                                                 final double max, final double min) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection
                            .zRevRangeByScoreWithScores(keys, max, min);
                }
            });
        }
        return null;
    }

    public Set<Tuple> zrangeByScoreWithScores(final Object key,
                                              final double min, final double max, final int offset,
                                              final int count) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRangeByScoreWithScores(keys, min, max,
                            offset, count);
                }
            });
        }
        return null;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final Object key,
                                                 final double max, final double min, final int offset,
                                                 final int count) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRevRangeByScoreWithScores(keys, max,
                            min, offset, count);
                }
            });
        }
        return null;
    }

    /**
     * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremrangeByScore(final Object key, final double start,
                                 final double end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRemRangeByScore(keys, start, end);
                }
            });
        }
        return null;
    }

    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score
     * 值递增(从小到大)次序排列。
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<Object> zRangeByScore(final Object key, final double min,
                                     final double max) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRangeByScore(keys, max, min);
                    Set<Object> data = Sets.newHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    public Set<Object> zRangeByScore(final Object key, final double min,
                                     final double max, final int offset, final int count) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRangeByScore(keys, max, min,
                            offset, count);
                    Set<Object> data = Sets.newHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    public Set<Tuple> zRangeByScoreWithScores(final Object key,
                                              final double min, final double max) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRangeByScoreWithScores(keys, max, min);
                }
            });
        }
        return null;
    }

    public Set<Tuple> zRangeByScoreWithScores(final Object key,
                                              final double min, final double max, final int offset,
                                              final int count) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRangeByScoreWithScores(keys, max, min,
                            offset, count);
                }
            });
        }
        return null;
    }

    public Set<Object> zRevRangeByScore(final Object key, final double max,
                                        final double min) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRevRangeByScore(keys, max,
                            min);
                    Set<Object> data = Sets.newHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    public Set<Object> zRevRangeByScore(final Object key, final double max,
                                        final double min, final int offset, final int count) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Object>>() {
                public Set<Object> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    Set<byte[]> set = connection.zRevRangeByScore(keys, max,
                            min, offset, count);
                    Set<Object> data = Sets.newHashSet();
                    for (byte[] s : set) {
                        data.add(serializer.deserialize(s));
                    }
                    return data;
                }
            });
        }
        return null;
    }

    public Set<Tuple> zRevRangeByScoreWithScores(final Object key,
                                                 final double max, final double min) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection
                            .zRevRangeByScoreWithScores(keys, max, min);
                }
            });
        }
        return null;
    }

    public Set<Tuple> zRevRangeByScoreWithScores(final Object key,
                                                 final double max, final double min, final int offset,
                                                 final int count) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
                public Set<Tuple> doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRevRangeByScoreWithScores(keys, max,
                            min, offset, count);
                }
            });
        }
        return null;
    }

    public Long zRemRangeByScore(final Object key, final double start,
                                 final double end) {
        if (redisTemplate != null) {
            return redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    byte[] keys = serializer.serialize(key);
                    return connection.zRemRangeByScore(keys, start, end);
                }
            });
        }
        return null;
    }

    /**
     * 容器初始化Bean前
     */
    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        if (redisTemplate != null) {
            serializer = (RedisSerializer<Object>) redisTemplate.getDefaultSerializer();
        }
    }

    /**
     * 容器销毁Bean前
     */
    @Override
    public void destroy() throws Exception {

    }

}
