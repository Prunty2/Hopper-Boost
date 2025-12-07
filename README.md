# ⚡ Hopper Boost

A simple Minecraft Fabric mod that lets you control hopper transfer speed with a gamerule.

## ✨ Features

- **Configurable Speed**: Use `/gamerule hopperSpeed X.X` to set hopper transfer speed
- **1.0** = Vanilla speed (8 tick cooldown)
- **2.0** = Double speed (4 tick cooldown)
- **0.5** = Half speed (16 tick cooldown)
- Supports any value from 0.1 to 100.0

## 📦 Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) 🧵
2. Install [Fabric API](https://modrinth.com/mod/fabric-api) 📚
3. Download this mod and place it in your `mods` folder 📁

## 🎮 Usage

```
/gamerule hopperSpeed 2.0
```

## 🔨 Building

```bash
./gradlew build
```

The built jar will be in `build/libs/`.

## 📄 License

MIT License
