package javaPack;

public class Player
{

    private static int numPlayers = 0;

    private int x;
    private int y;
    private int z;
    private int direction; // 1-6
    private int hp;        // >= 0
    private String name;

    // Default constructor
    public Player()
    {
        numPlayers++;
        this.name = "P" + numPlayers;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.hp = 20;
        this.direction = 1;
    }

    // Constructor with name and position
    public Player(String name, int x, int y, int z)
    {
        numPlayers++;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hp = 20;
        this.direction = 1;
    }

    // Full constructor
    public Player(String name, int x, int y, int z, int health, int direction)
    {
        numPlayers++;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;

        if (health < 0)
        {
            this.hp = 0;
        }
        else
        {
            this.hp = health;
        }

        if (direction >= 1 && direction <= 6)
        {
            this.direction = direction;
        }
        else
        {
            this.direction = 1;
        }
    }

    // Accessors
    public int getNumPlayers()
    {
        return numPlayers;
    }

    public String getName()
    {
        return name;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    public int getHp()
    {
        return hp;
    }

    public int getDirection()
    {
        return direction;
    }

    // String output
    public String toString()
    {
        return "Name: " + name + "\n"
                + "Health: " + hp + "\n"
                + "Coordinates: X " + x + " Y " + y + " Z " + z + "\n"
                + "Direction: " + direction;
    }

    // Mutators
    public void setHp(int hp)
    {
        if (hp < 0)
        {
            this.hp = 0;
        }
        else
        {
            this.hp = hp;
        }
    }

    public void setDirection(int direction)
    {
        if (direction >= 1 && direction <= 6)
        {
            this.direction = direction;
        }
    }

    // Move player
    public void move(int direction, int units)
    {
        if (units < 0)
        {
            units = -units;
        }

        if (direction == 1)
        {
            x += units;
        }
        else if (direction == 2)
        {
            x -= units;
        }
        else if (direction == 3)
        {
            y += units;
        }
        else if (direction == 4)
        {
            y -= units;
        }
        else if (direction == 5)
        {
            z += units;
        }
        else if (direction == 6)
        {
            z -= units;
        }
    }

    // Teleport by coordinates
    public void teleport(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Teleport to another player
    public void teleport(Player player)
    {
        this.x = player.x;
        this.y = player.y;
        this.z = player.z;
    }

    // Attack another player
    public void attack(Player player, int damage)
    {
        if (damage < 0)
        {
            damage = 0;
        }

        int before = player.hp;

        player.hp = player.hp - damage;

        if (player.hp < 0)
        {
            player.hp = 0;
        }

        int actualDamage = before - player.hp;
        this.hp = this.hp + (actualDamage / 2);
    }

    // Distance methods
    public double getDistance(int x, int y, int z)
    {
        int dx = x - this.x;
        int dy = y - this.y;
        int dz = z - this.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double getDistance(Player player)
    {
        return getDistance(player.x, player.y, player.z);
    }
}
