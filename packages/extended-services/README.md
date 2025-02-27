## Extended Services

Powers the DMN Runner and Dev deployments features.

## Parameters

- `-p <PORT_NUMBER>`: Sets app port, otherwise it will use config.yaml port.

## Note to Fedora users

### Run

To run this application on Fedora, it's necessary to install the App Indicator lib:
Firstly install the following packages:

- `sudo dnf install libappindicator-gtk3`

Then, enable the App Indicator extension
https://extensions.gnome.org/extension/615/appindicator-support/

## Misc

### How do I create the image.go?

Firstly install the 2goarray package
`go get github.com/cratonica/2goarray`

Then convert the image to a .go file
`cat icon2.png | /Users/aparedes/go/bin/2goarray Data images > icon.go`

### API

#### `/`

The root route is a proxy and will forward your requests to the autogenerated port

#### `/ping`

[GET] returns the API version, the proxy port and IP and the KIE Sandbox URL.

```json
{
  "version": "0.0.0",
  "proxy": {
    "ip": "127.0.0.1",
    "port": "21345",
    "insecureSkipVerify": false
  },
  "kieSandboxUrl": "https://sandbox.kie.org/#/",
  "started": true
}
```

### Next Steps

- Limit GraalVM Heap Size
