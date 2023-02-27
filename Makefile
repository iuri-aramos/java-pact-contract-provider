GIT_COMMIT?=$(shell git rev-parse --short HEAD)
ENV?=dev
PACTICIPANT := BeerService
PROVIDER_PATH := /src/test/java/io/pact/workshop/product_service/PactVerificationTest.java
PACT_CLI="docker run --rm -w ${PWD} -v ${PWD}:${PWD} -e PACT_BROKER_BASE_URL -e PACT_BROKER_TOKEN pactfoundation/pact-cli:latest"

build: pacticipant test deploy_to_env can_i_deploy

test:
	mvn clean verify -Dpact.verifier.publishResults=true \
 					 -Dpact.provider.branch=${ENV} \
 					 -Dpact.provider.version=${GIT_COMMIT}

pacticipant:
	@"${PACT_CLI}" pact-broker create-or-update-pacticipant --name=${PACTICIPANT}

deploy_to_env:
	@"${PACT_CLI}" pact-broker record-deployment --pacticipant=${PACTICIPANT} --environment=${ENV} --version=${GIT_COMMIT}

can_i_deploy:
	@"${PACT_CLI}" pact-broker can-i-deploy --pacticipant=${PACTICIPANT} --to-environment=${ENV} --version=${GIT_COMMIT}

echo:
	echo @"${PACT_CLI}" pact-broker can-i-deploy --pacticipant=${PACTICIPANT} --to-environment=${ENV} --version=${GIT_COMMIT}