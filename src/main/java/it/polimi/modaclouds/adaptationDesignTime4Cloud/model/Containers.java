//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.21 at 12:45:03 PM CEST 
//


package it.polimi.modaclouds.adaptationDesignTime4Cloud.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="container" type="{}container" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timestepDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="optimizationWindowsLenght" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "container"
})
@XmlRootElement(name = "containers")
public class Containers {

    @XmlElement(required = true)
    protected List<Container> container;
    @XmlAttribute(name = "timestepDuration", required = true)
    protected int timestepDuration;
    @XmlAttribute(name = "optimizationWindowsLenght", required = true)
    protected int optimizationWindowsLenght;

    /**
     * Gets the value of the container property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the container property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContainer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Container }
     * 
     * 
     */
    public List<Container> getContainer() {
        if (container == null) {
            container = new ArrayList<Container>();
        }
        return this.container;
    }

    /**
     * Gets the value of the timestepDuration property.
     * 
     */
    public int getTimestepDuration() {
        return timestepDuration;
    }

    /**
     * Sets the value of the timestepDuration property.
     * 
     */
    public void setTimestepDuration(int value) {
        this.timestepDuration = value;
    }

    /**
     * Gets the value of the optimizationWindowsLenght property.
     * 
     */
    public int getOptimizationWindowsLenght() {
        return optimizationWindowsLenght;
    }

    /**
     * Sets the value of the optimizationWindowsLenght property.
     * 
     */
    public void setOptimizationWindowsLenght(int value) {
        this.optimizationWindowsLenght = value;
    }

}
