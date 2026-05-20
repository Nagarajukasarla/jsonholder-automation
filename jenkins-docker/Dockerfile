FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y \
    docker-cli \
    maven

CMD ["/usr/bin/tini", "--", "/usr/local/bin/jenkins.sh"]