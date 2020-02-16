var jackrabbit = require('@pager/jackrabbit');
var rabbit = jackrabbit(process.env.RABBIT_URL || "amqp://localhost");

rabbit
  .default()
  .queue({ name: 'hello' })
  .consume(onMessage, { noAck: true });

function onMessage(data) {
  console.log('received:', data);
}

rabbit
  .default()
  .publish('Hello World!', { key: 'hello' })
  .on('drain', rabbit.close);
