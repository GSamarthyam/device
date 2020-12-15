package before;

class RemoteControlledVolumeDevice
{
    private int volume;
    private final int MAX_VOLUME = 10;

    public RemoteControlledVolumeDevice(int volume)
    {
        if (volume > MAX_VOLUME)
        {
            this.volume = MAX_VOLUME;
        }
        else
        {
            this.volume = volume;
        }
    }

    public void resetVolume()
    {
        volume = 0;
    }

    public void setToMaxVolume()
    {
        volume = MAX_VOLUME;
    }

    public void increaseVolume()
    {
        if (volume < MAX_VOLUME)
        {
            volume++;
        }
    }

    public void decreaseVolume()
    {
        if(volume > 0)
            volume--;
    }
}
