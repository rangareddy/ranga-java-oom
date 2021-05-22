# java.lang.OutOfMemoryError: request `<bytes>` for `<reason>`. Out of swap space?

The error indicates that the swap space has also been used up, and the new allocation attempt failed due to lack of physical memory and swap space.

When the byte allocation request from the native heap fails and the native heap is almost exhausted, the JVM will throw this exception. This message indicates the size (in bytes) of the allocation failure and the reason for the memory request.

java.lang.OutOfMemoryError: Out of swap space error is often caused by operating system level issues:

* The swap space configured by the operating system is insufficient.
* The physical memory has been exhausted.
* The local memory leak of the application (native leak), for example, keep applying for local memory, but not releasing it.
* Another process on the system is consuming all memory resources.

**swap space** is a space used by the underlying operating system to store the contents in lieu of RAM in case it fills up. Every time you start your application, JVM allocates the appropriate amount of memory to all the regions depending on your VM parameter. This means, your application has an upper limit for the memory it can use. Hence, if your application happens to request more memory than the allocated limit, the operating system uses the swap space from the hard drive as a virtual memory. If the swap memory space is also fully consumed, JVM throws the error java.lang.OutOfMemoryError: request size bytes for reason. Are you out of swap space?

<p align="center">
  <img src="https://github.com/rangareddy/ranga-java-oom/blob/main/images/OOM_SwapSpace.png">
</p>

When the total memory requested by the JVM is greater than the available physical memory, the operating system begins to swap the memory from the memory to the hard drive. The lack of physical memory and swap space causes the allocation to fail.

It is also possible that the application fails due to a native leak, for example, if application or library code continuously allocates memory but does not release it to the operating system.

**Solution:** 

1. Increase the size of the swap partition
```sh
swapoff -a 
dd if =/dev/zero of=swapfile bs=1024 count=655360 
mkswap swapfile 
swapon swapfile
```
2. Increase the size of the machine's memory.
3. Other service processes can be selectively split out.
