all:
	cd core && mvn clean install
	cd systems/base && mvn clean install
	cd systems/sr3 && mvn clean install