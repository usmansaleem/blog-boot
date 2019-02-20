#Uzi Blog 

Rest backend for custom Blogging frontend.

**Technologies**: Spring Boot 2

**CI**: CircleCI. 

**Hosting**: https://www.scaleway.com/ 

**Author**: Usman Saleem

**Build**:
`./gradlew clean build `

**CircleCI Configuration**
- Build Settings/Environment variables SSH_HOST and SSH_USER
- Permissions/SSH Permissions. Add SSH Key of host. Note the Fingerprint and update `.circleci/config.yml`

**Host Configuration**

- Run: `apt update upgrade`
- Add system user: 
  -   `adduser --system --no-create-home --group bootapp`
  -   `mkdir /var/blogboot`
  -   `chown bootapp:bootapp /var/blogboot`
- Optionally install OpenJDK, specially if in AArch64 environment
  - https://adoptopenjdk.net/releases.html?variant=openjdk11&jvmVariant=hotspot#aarch64_linux
  - `wget https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.2%2B7/OpenJDK11U-jre_aarch64_linux_hotspot_11.0.2_7.tar.gz`
  - `cd /var/blogboot && tar -xvzf ~/OpenJDK11U-jre_aarch64_linux_hotspot_11.0.2_7.tar.gz`  
- Systemd Configuration. See https://gist.github.com/usmansaleem/ba4a228f6626bfcf01e96f5323705714. Update values as per set up.
- nginx Reverse Proxy Configuration.
  - `apt-get install nginx`
  - See https://gist.github.com/usmansaleem/3b4097b3298505c10011905beddc0f56
  - Create symlink in sites-enabled
- certbot SSL Configuration.
  - https://certbot.eff.org/lets-encrypt/ubuntubionic-nginx
- iptable restrictions

See this project in action at: https://usmans.info/

**Contact**: Usman Saleem (usman at usmans dot info)


