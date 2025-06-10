# A2A Client for Java

## Usage

```java
import souchan.a2a.A2A;
import souchan.a2a.models;

class Foo {
    public static void main(String... args) {
        A2AClient a2aClient = A2A.getClient("<A2A Server Domain>","<Security Scheme Name>","<Credential>");

        MessageSendParams messageSendParams = MessageSendParams
                .builder()
                
                
        a2aClient.sendMessage()
    
    }
}
```
