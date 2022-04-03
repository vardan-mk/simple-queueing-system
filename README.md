# simple-queueing-system
Simple queue system to provide different services per queue and people can join and wait there  turn.

# for testing purpose you can find following curl's request
everything is managed in internal memory, so don't need any database, just postman or curl

## for users there exist 2 endpoints
Get: to see existing queues and services in each queue and servicers count for this queue
#### curl --location --request GET 'localhost:8080/user/queues'

Post: to join needed queue service you need to use one of curl's example, 
and as response you will get the count of waiters in this queue and amount of time to wait
#### curl --location --request POST 'localhost:8080/user/queues/paymentQueue/services/phonePayments/join/3'
#### curl --location --request POST 'localhost:8080/user/queues/paymentQueue/services/communalPayments/join/2'
#### curl --location --request POST 'localhost:8080/user/queues/repairQueue/services/phoneRepair/join/3'
Note: the right endpoint is: curl --location --request POST 'localhost:8080/user/queues/{queueId}/services/{serviceId}/join/{userId}'

## for servicers there exist 2 endpoints
Get: to see existing services in the queue and users count waiting to be served in current service

#### curl --location --request GET 'localhost:8080/servicer/queues/repairQueue/info/1'

Note: the right endpoint is: 
curl --location --request GET 'localhost:8080/servicer/queues/{queueId}/info/{servicerId}'

Post: to summon a user in the current service queue service you need to use one of curl's example,
and as response you will get is the id of summoned user and the next waiting user id this queue.

Note: if there is now users in the queue you will get message about it, 
and if after summon there is no waiting user you will get corresponding message

#### curl --location --request POST 'localhost:8080/servicer/queues/paymentQueue/services/phonePayments/servicers/1/summon'
#### curl --location --request POST 'localhost:8080/servicer/queues/paymentQueue/services/communalPayments/servicers/2/summon'
#### curl --location --request POST 'localhost:8080/servicer/queues/repairQueue/services/phoneRepair/servicers/3/summon'

Note: the right endpoint is: 
curl --location --request POST 'localhost:8080/servicer/queues/{queueId}/services/{serviceId}/servicers/{{servicerId}}/summon'

## To keep simplicity and because lack of time, there exist some restrictions and assumptions
Queues and their services are preconfigured and setup at the start of the app,
you can't add new queue or service to existing queue.

skipped users or servicers login/registration/validation.

availability for servicers to see only services info for corresponding queue which one they are assigned.

This functionality partially covered with endpoint, that servicers use where mentioned queue id,
assuming that current survicers can't see other queues.

And a lot of staff from real life, like users don't wait for his turn and leave the queue, 
or some users try to join same service queue multiple times.

Also a case when the count of users in the queue are less than count of servicers,
and which one of servicers going to serve those users and which one not.

## Missing parts in the code are: partially exception handling and unittests.