var jackrabbit = require('@pager/jackrabbit');
var rabbit = jackrabbit(process.env.RABBIT_URL || "amqp://localhost");

rabbit
  .default()
  .queue({ name: 'hello' })
  .consume(onMessage, { noAck: true });

function onMessage(data) {
  console.log('received:', data);
}

console.log("Hey guys!")
