from PIL import Image
import os
import re

def pngs_to_pdf(input_folder, output_pdf, compression_quality=95):
    if not os.path.exists(input_folder):
        print(f"Error: Input folder '{input_folder}' does not exist.")
        return

    # Print all files in folder for debugging
    all_files = os.listdir(input_folder)
    print(f"Found {len(all_files)} files in the folder:")
    for file in all_files:
        print(f"  - {file}")

    # Get all image files from the folder
    valid_extensions = ('.png', '.PNG', '.jpg', '.JPG', '.jpeg', '.JPEG')
    images = [f for f in all_files if f.lower().endswith(tuple(ext.lower() for ext in valid_extensions))]
    
    if not images:
        print("No image files found in the folder.")
        print(f"Looking for files with extensions: {valid_extensions}")
        return
    
    print(f"Found {len(images)} image files:")
    for img in images:
        print(f"  - {img}")
    
    # Improved sorting - try to extract number from filename, fallback to alphabetical
    def get_sort_key(filename):
        # Try to extract number from the filename
        match = re.search(r'(\d+)', os.path.splitext(filename)[0])
        if match:
            return int(match.group(1))
        else:
            # Fall back to alphabetical sorting if no number found
            return os.path.splitext(filename)[0]
    
    images.sort(key=get_sort_key)
    print("Sorted images:")
    for img in images:
        print(f"  - {img}")
    
    image_list = []
    for img_file in images:
        img_path = os.path.join(input_folder, img_file)
        try:
            # Open image and convert to RGB (required for PDF)
            img = Image.open(img_path).convert("RGB")
            # Don't resize the image to preserve quality
            image_list.append(img)
        except Exception as e:
            print(f"Error opening {img_file}: {str(e)}")
    
    if not image_list:
        print("No valid images could be processed.")
        return
    
    # Ensure output directory exists
    output_dir = os.path.dirname(output_pdf)
    if output_dir and not os.path.exists(output_dir):
        os.makedirs(output_dir)
        
    # Save images as a PDF with optimized compression
    try:
        print(f"Saving PDF with quality level: {compression_quality}%")
        
        # PDF-specific options for compression without losing quality
        pdf_options = {
            "save_all": True,
            "append_images": image_list[1:],
            "resolution": 100.0,  # Set DPI (dots per inch)
            "quality": compression_quality,  # JPEG compression quality (higher = better quality)
            "optimize": True     # Optimize PDF structure
        }
        
        image_list[0].save(output_pdf, "PDF", **pdf_options)
        
        # Check the output file size
        if os.path.exists(output_pdf):
            file_size = os.path.getsize(output_pdf) / (1024 * 1024)  # Size in MB
            print(f"PDF successfully saved as {output_pdf} (Size: {file_size:.2f} MB)")
        else:
            print(f"Error: PDF file was not created at {output_pdf}")
            
    except Exception as e:
        print(f"Error saving PDF: {str(e)}")
        import traceback
        traceback.print_exc()

# Example usage
if __name__ == "__main__":
    # Use input from the user for the folders
    print("PNG to PDF Converter")
    print("====================")
    
    default_input = "C:/Users/ashir/Downloads/PEP-1-20250403T192249Z-001/PEP-1"
    default_output = "C:/Users/ashir/Downloads/PEP-1-20250403T192249Z-001/output.pdf"
    
    print(f"Default input folder: {default_input}")
    input_folder = input("Enter input folder path (or press Enter to use default): ").strip()
    if not input_folder:
        input_folder = default_input
        
    print(f"Default output PDF: {default_output}")
    output_pdf = input("Enter output PDF path (or press Enter to use default): ").strip()
    if not output_pdf:
        output_pdf = default_output
    
    # Ensure output has .pdf extension
    if not output_pdf.lower().endswith('.pdf'):
        output_pdf += '.pdf'
    
    # Ask for compression quality
    print("\nCompression Quality (75-100, higher = better quality but larger file):")
    print("- 95: Recommended for high quality with good compression")
    print("- 85: Good balance between quality and file size")
    print("- 75: Maximum compression, might affect image quality")
    
    try:
        quality = int(input("Enter compression quality (75-100) or press Enter for 95: ").strip() or "95")
        quality = max(75, min(100, quality))  # Ensure value is between 75 and 100
    except ValueError:
        print("Invalid input, using default quality of 95")
        quality = 95
        
    print(f"\nProcessing images from: {input_folder}")
    print(f"Output PDF will be: {output_pdf}")
    print(f"Using compression quality: {quality}%")
    print("====================")
    
    pngs_to_pdf(input_folder, output_pdf, quality)
