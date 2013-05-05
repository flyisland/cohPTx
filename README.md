cohPTx
======

example of coherence partition-level transaction

## Hints ##
1. In order to use `PofExtractor` with `InvocableMapHelper.query()` method in the EP, an Index must be created first with the cache.
1. `tangosol.coherence.distributed.threads`'s default value is 1, thus time consuming EP will block the `Distributed Cache Service`. Increase it to improve the concurrency.

## run ##
1. `gradle -q cacheServer` to start default cache server with `cacheconfig.xml'
1. `gradle -q cc -Pcc=LoadCache` to initial cache `accounts` and `balances`
1. `gradle -q cc -Pcc=UpdateBalanceByEP` to update associated balances.
1. `gradle -q cc -Pcc=ReadBalance` to keep reading specific balance from cache 'balances' to check if the `cache.get()` operation is blocked.

