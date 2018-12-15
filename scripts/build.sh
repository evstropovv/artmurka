#/bin/bash
cd ..
GRADLE="./gradlew"
$GRADLE assembleDebug
$GRADLE crashlyticsUploadDistributionDebug
#RELEASEFILE="${CIRCLE_ARTIFACTS}/artmurka-debug-${CIRCLE_BUILD_NUM}-${CIRCLE_SHA1}.apk"
#cp ./app/build/outputs/apk/debug/app-debug.apk ${RELEASEFILE}