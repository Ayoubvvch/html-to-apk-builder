#!/usr/bin/env node

/**
 * Configuration Script
 * Reads app-config.json and updates Android manifest and resources
 */

const fs = require('fs');
const path = require('path');

const configPath = path.join(__dirname, '..', 'app-config.json');
const config = JSON.parse(fs.readFileSync(configPath, 'utf8'));

console.log('📋 App Configuration:');
console.log(`   Name: ${config.appName}`);
console.log(`   Package: ${config.appId}`);
console.log(`   Version: ${config.versionName}`);
console.log('');

// Update AndroidManifest.xml
const manifestPath = path.join(__dirname, '..', 'android', 'app', 'src', 'main', 'AndroidManifest.xml');
if (fs.existsSync(manifestPath)) {
    let manifest = fs.readFileSync(manifestPath, 'utf8');
    manifest = manifest.replace(/package="[^"]*"/, `package="${config.appId}"`);
    fs.writeFileSync(manifestPath, manifest);
    console.log('✅ Updated AndroidManifest.xml');
}

// Update strings.xml
const stringsPath = path.join(__dirname, '..', 'android', 'app', 'src', 'main', 'res', 'values', 'strings.xml');
if (fs.existsSync(stringsPath)) {
    let strings = fs.readFileSync(stringsPath, 'utf8');
    strings = strings.replace(/<string name="app_name">[^<]*<\/string>/, `<string name="app_name">${config.appName}</string>`);
    fs.writeFileSync(stringsPath, strings);
    console.log('✅ Updated strings.xml');
}

// Update build.gradle.kts
const buildGradlePath = path.join(__dirname, '..', 'android', 'app', 'build.gradle.kts');
if (fs.existsSync(buildGradlePath)) {
    let buildGradle = fs.readFileSync(buildGradlePath, 'utf8');
    
    // Update namespace
    buildGradle = buildGradle.replace(/namespace = "[^"]*"/, `namespace = "${config.appId}"`);
    
    // Update applicationId
    buildGradle = buildGradle.replace(/applicationId = "[^"]*"/, `applicationId = "${config.appId}"`);
    
    // Update versionName
    buildGradle = buildGradle.replace(/versionName = "[^"]*"/, `versionName = "${config.versionName}"`);
    
    fs.writeFileSync(buildGradlePath, buildGradle);
    console.log('✅ Updated build.gradle.kts');
}

console.log('');
console.log('🎯 Configuration complete!');
console.log('');
