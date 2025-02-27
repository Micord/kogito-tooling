# kn-plugin-workflow

`kn-plugin-workflow` is a plugin of the Knative Client, to enable users to quickly set up a local SonataFlow project from the command line.

[Read the documentation](https://kiegroup.github.io/kogito-docs/serverlessworkflow/main/tooling/kn-plugin-workflow-overview.html)

## Build from source

All the commands in this section should be performed in the monorepo root.

### Prerequisites

- Node `>= 18.14.0` _(To install, follow these instructions: https://nodejs.org/en/download/package-manager/)_
- pnpm `7.26.3` _(To install, follow these instructions: https://pnpm.io/installation)_
- Go `1.20.5` _(To install, follow these instructions: https://go.dev/doc/install)_

#### Prerequisites for running integration tests

- docker _(To install, follow these instructions: https://docs.docker.com/engine/install)_
- podman _(To install, follow these instructions: https://podman.io/docs/installation)_

### Installing and linking dependencies

The following command will install the `kn-plugin-workflow` dependencies and link it with any other monorepo
package that is listed in the `package.json`:

- `pnpm bootstrap -F "@kie-tools/kn-plugin-workflow..."`

### Building

It has two different strategies to build the `kn-plugin-workflow`:

- `build:dev` _(The build will generate one artifact that is compatible with your local machine)_
- `build:prod` _(The build will generate artifacts for all available architecture and run the available tests)_

To build the `kn-plugin-workflow` run the following command:

- `pnpm -r -F "@kie-tools/kn-plugin-workflow..." <build-strategy>`

### Integration Tests

To build the `kn-plugin-workflow` and run integration tests, use the following commands:

- `export KIE_TOOLS_BUILD__runIntegrationTests=true`
- `pnpm -r -F "@kie-tools/kn-plugin-workflow..." build:prod`
