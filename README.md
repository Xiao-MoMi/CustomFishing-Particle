## Particle Expansion for CustomFishing
### How to install
① Put the jar into /CustomFishing/expansions/action/ folder\
② Run command /cfishing reload\
③ Enjoy

### Particle Properties
##### Required
```yaml
# https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Particle.html
particle: TYPE

pos: player/other

shape: circle/arc/line/cube/sphere
```
##### Optional
```yaml
count: 1

offsetX: 0
offsetY: 0
offsetZ: 0

# Speed
extra: 0

# Only available when particle is ITEM_CRACK
itemStack: 
  material: paper
  custom-model-data: 1000

# Only available when particle is REDSTONE/DUST_COLOR_TRANSITION
color: 255,0,0
scale: 1

# Only available when particle is DUST_COLOR_TRANSITION
to-color: 0,0,255

# Define the center of the particles
x1: 0
y1: '{papi} + 5'
z1: 0

# Rotate the particles in 3D
xAxisRotation: 90
zAxisRotation: '{papi} * 20'
yAxisRotation: 90
```
### Particle Shape
##### Circle
```yaml
# the radius of the circle
radius: 10

# density of particles
step: 0.5
```
##### Arc
```yaml
radius: 10
step: 0.5

startAngle: '{yaw} + 50'
angle: 180
```
##### Sphere
```yaml
radius: 10
sample: 100
```
##### Line
```yaml
x2: 10
y2: 5
z2: 7
step: 0.5
```
##### Cube
```yaml
x2: 10
y2: 5
z2: 7
step: 0.5
```
### Examples
#### Wave
![e1](https://github.com/Xiao-MoMi/CustomFishing-Particle/assets/70987828/2fa4f9fe-a545-41b9-ac38-e23f881291e4)
```yaml
particle_1:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 135,206,250
    shape: circle
    radius: 2
    step: 1
    y1: '0.2'
particle_2:
  type: delay
  value:
    delay: 3
    actions:
      action_1:
        type: particle
        value:
          particle: REDSTONE
          pos: other
          color: 100,149,237
          shape: circle
          radius: 3
          step: 1
          y1: '0.5'
particle_3:
  type: delay
  value:
    delay: 6
    actions:
      action_1:
        type: particle
        value:
          particle: REDSTONE
          pos: other
          color: 65,105,225
          shape: circle
          radius: 4
          step: 1
          y1: '0.4'
```
#### Rainbow
![e2](https://github.com/Xiao-MoMi/CustomFishing-Particle/assets/70987828/4ff8c196-845c-4930-a664-6154f4dd7421)
```yaml
particle_1:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 153,50,204
    shape: arc
    radius: 2
    step: 1
    y1: '1'
    zAxisRotation: 90
    xAxisRotation: '-{yaw} + 90'  # You have to register {yaw} in config.yml before using it. The original placeholder is %player_yaw%
    startAngle: -75
    angle: 150
particle_2:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 65,105,225
    shape: arc
    radius: 2
    step: 1
    y1: '1.1'
    zAxisRotation: 90
    xAxisRotation: '-{yaw} + 90'
    startAngle: -76
    angle: 152
particle_3:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 72,209,204
    shape: arc
    radius: 2.05
    step: 1
    y1: '1.2'
    zAxisRotation: 90
    xAxisRotation: '-{yaw} + 90'
    startAngle: -77
    angle: 154
particle_4:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 0,255,127
    shape: arc
    radius: 2.1
    step: 1
    y1: '1.3'
    zAxisRotation: 90
    xAxisRotation: '-{yaw} + 90'
    startAngle: -78
    angle: 156
particle_5:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 255,255,0
    shape: arc
    radius: 2.15
    step: 1
    y1: '1.4'
    zAxisRotation: 90
    xAxisRotation: '-{yaw} + 90'
    startAngle: -79
    angle: 158
particle_6:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 255,165,0
    shape: arc
    radius: 2.2
    step: 1
    y1: '1.5'
    zAxisRotation: 90
    xAxisRotation: '-{yaw} + 90'
    startAngle: -80
    angle: 160
particle_7:
  type: particle
  value:
    particle: REDSTONE
    pos: other
    color: 255,69,0
    shape: arc
    radius: 2.25
    step: 1
    y1: '1.6'
    zAxisRotation: 90
    xAxisRotation: '-{yaw} + 90'
    startAngle: -81
    angle: 162
```
