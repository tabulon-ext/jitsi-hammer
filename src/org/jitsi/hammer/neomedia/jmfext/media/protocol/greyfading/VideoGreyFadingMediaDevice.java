/*
 * Jitsi-Hammer, A traffic generator for Jitsi Videobridge.
 * 
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
 
package org.jitsi.hammer.neomedia.jmfext.media.protocol.greyfading;

import java.awt.Dimension;

import javax.media.*;
import javax.media.format.*;
import javax.media.protocol.*;

import org.jitsi.impl.neomedia.device.*;
import org.jitsi.service.neomedia.*;


/**
 * Implements a <tt>MediaDevice</tt> which provides a fading animation from
 * white to black to white... in form of video.
 *
 * @author Thomas Kuntz
 */
public class VideoGreyFadingMediaDevice
    extends MediaDeviceImpl
{
    /**
     * The list of <tt>Format</tt>s supported by the
     * <tt>DataSource</tt> and instances of <tt>VideoGreyFadingStream</tt>.
     */
    public static final Format[] SUPPORTED_FORMATS
        = new Format[]
        		{
                new RGBFormat(
                	 new Dimension(640,480), // size
                     Format.NOT_SPECIFIED, // maxDataLength
                     Format.byteArray, // dataType
                     10, // frameRate
                     32, // bitsPerPixel
                     2 /* red */,
                     3 /* green */,
                     4 /* blue */)
        		};
    
 
    /**
     * Initializes a new <tt>VideoGreyFadingMediaDevice</tt>
     */
    public VideoGreyFadingMediaDevice()
    {
        super(new CaptureDeviceInfo(
                    "GreyFadingVideo",
                    new MediaLocator("greyfading:"),
                    VideoGreyFadingMediaDevice.SUPPORTED_FORMATS),
                MediaType.VIDEO);
    }

    
    /**
     * {@inheritDoc}
     *
     * Overrides the super implementation to initialize a <tt>CaptureDevice</tt>
     * without asking FMJ to initialize one for a <tt>CaptureDeviceInfo</tt>.
     */
    @Override
    protected CaptureDevice createCaptureDevice()
    {
        return new DataSource();
    }

    
    /**
     * {@inheritDoc}
     *
     * Overrides the super implementation to always return
     * {@link MediaDirection#SENDRECV} because this instance stands for a relay
     * and because the super bases the <tt>MediaDirection</tt> on the
     * <tt>CaptureDeviceInfo</tt> which this instance does not have.
     */
    @Override
    public MediaDirection getDirection()
    {
        return MediaDirection.SENDRECV;
    }
}
