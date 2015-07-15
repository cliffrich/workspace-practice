Feature: Create Spring Integration Pub/Sub Middleware RabbitMQ exchange template

  Scenario: Publish messages to RabbitMQ exchange called broker.replace and to a queue called broker.replace.queue
    When I play a message to RabbitMQ exchange
    Then I should see zero messages in DLQs
    And I should receive the message in the receiver
