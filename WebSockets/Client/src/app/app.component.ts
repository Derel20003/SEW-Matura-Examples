import { Component } from '@angular/core';
import { WebSocketMessage, WebSocketSubject } from "rxjs/internal/observable/dom/WebSocketSubject";
import { webSocket } from "rxjs/webSocket";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  myWebSocket?: WebSocketSubject<WebSocketMessage>
  text = ""

  connect() {
    this.myWebSocket = webSocket({
      url: "ws://localhost:8080/start-websocket/dom",
      serializer: msg => msg,
      deserializer: msg => msg.data
    })
    this.myWebSocket.subscribe(msg => {
      console.log(msg)
    })
    console.log(this.myWebSocket)
  }

  disconnect() {
    this.myWebSocket?.complete();
  }

  send() {
    this.myWebSocket?.next(this.text)
  }

  title = 'WebClient';
}
