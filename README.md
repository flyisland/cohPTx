cohPTx
======

example of coherence partition-level transaction

## Hints ##
1. In order to use "PofExtractor" with "InvocableMapHelper.query()" method in the EP, an Index must be created first with the cache.
1. "tangosol.coherence.distributed.threads"'s default value is 1, thus time consuming EP will block the **Distribute Service**. Increase it to improve the concurrency.
